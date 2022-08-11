package com.gft.receitas.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gft.receitas.entities.Ingrediente;
import com.gft.receitas.entities.Item;
import com.gft.receitas.entities.Receita;
import com.gft.receitas.entities.UnidadeMedida;
import com.gft.receitas.repositories.IngredienteRepository;
import com.gft.receitas.repositories.ItemRepository;
import com.gft.receitas.repositories.ReceitaRepository;
import com.gft.receitas.repositories.UnidadeMedidaRepository;

@Service
public class ReceitaService {

	@Autowired
	private ReceitaRepository receitaRepository;
	
	@Autowired
	private IngredienteRepository ingredienteRepository;

	@Autowired
	private UnidadeMedidaRepository unidadeMedidaRepository;
	
	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private ItemService itemService;
	
	public static String capitalize(String str) {
	    if(str == null || str.isEmpty()) {
	        return str;
	    }
	    str = str.toLowerCase().trim();
	    return str.substring(0, 1).toUpperCase() + str.substring(1);
	}

	// i % 3 trim()
	// resto 0 -> quantidade
	// resto 1 -> unidade de Medida
	// resto 2 -> ingrediente
	public Receita salvarReceita (Receita receita) {
		receitaRepository.save(receita);
		List<Item> itens = itemService.listaItensPelaReceita(receita);
		if (itens != null) {
			for (int i = 0; i<itens.size(); i++) {
				itemService.excluirItem(itens.get(i).getId());
			}
		}
		String[] partes = receita.getInfo().split(";");
		Item item = new Item();
		
		for(int i = 0; i < partes.length; i++) {
			if (i%3 == 0) {	
				item.setReceita(receita);
				String numeroTransformar = partes[i].trim();
				int numero = Integer.parseInt(numeroTransformar);
				item.setQuantidade(numero);
				if (receita.getInfoFormatado() != null) {
					receita.setInfoFormatado(receita.getInfoFormatado() + capitalize(partes[i]) + " ");
				} else {
					receita.setInfoFormatado("【 " + capitalize(partes[i]) + " ");
				}
				
			} else if (i%3 == 1) {
				if(unidadeMedidaRepository.findByNome(capitalize(partes[i])) != null) {
					item.setUnidadeMedida(unidadeMedidaRepository.findByNome(capitalize(partes[i])));
					receita.setInfoFormatado(receita.getInfoFormatado() + capitalize(partes[i]) + " de ");
				} else {
					UnidadeMedida unidadeMedida = new UnidadeMedida();
					unidadeMedida.setNome(capitalize(partes[i]));
					unidadeMedidaRepository.save(unidadeMedida);
					receita.setInfoFormatado(receita.getInfoFormatado() + capitalize(partes[i]) + " de ");
					item.setUnidadeMedida(unidadeMedida);
				}
				
			} else if (i%3 == 2) {
				if (ingredienteRepository.findByNome(capitalize(partes[i])) != null) {
					item.setIngrediente(ingredienteRepository.findByNome(capitalize(partes[i])));
				} else {
					Ingrediente ingrediente = new Ingrediente();
					ingrediente.setNome(capitalize(partes[i]));
					ingredienteRepository.save(ingrediente);
					item.setIngrediente(ingrediente);
				}
				
				if(i != (partes.length - 1)) {
					receita.setInfoFormatado(receita.getInfoFormatado() + capitalize(partes[i]) + " 】【 ");
				} else {
					receita.setInfoFormatado(receita.getInfoFormatado() + capitalize(partes[i]) + " 】");
				}
				receitaRepository.save(receita);
				itemRepository.save(item);
				item = new Item();
				
			}
		}
		
		return null;
	}
	
	public Receita obterReceita (Long id) throws Exception {
		Optional<Receita> receita = receitaRepository.findById(id);
		
		if(receita.isEmpty()) {
			throw new Exception ("Receita não encontrada!");
		}
		
		return receita.get();
	}
	
	public List<Receita> listaReceita (String nome) {
		if(nome != null) {
			return receitaRepository.findByNomeContains(nome);
		}
		
		return listaReceitaCompleta();
	}
	
	public List<Receita> listaPesquisa (String nome, String ingrediente) {

		if(nome == "") {
			if(ingrediente != "") {
				return receitaRepository.findByItemIngredienteNomeContains(ingrediente);
			} else {
				return listaReceitaCompleta();
			}
		} else if (nome == null || ingrediente == null){
			return listaReceitaCompleta();
		} else {
			if(ingrediente != "") {
				List<Receita> listaTemIngrediente = receitaRepository.findByItemIngredienteNomeContains(ingrediente);
				List<Receita> listaTemNome = receitaRepository.findByNomeContains(nome);
				List<Receita> listaCerta = new ArrayList<Receita>(listaTemIngrediente);
				listaCerta.retainAll(listaTemNome);
				return listaCerta;
			} else {
				return receitaRepository.findByNomeContains(nome);
			}
		}
	}
		
	public List<Receita> listaReceitaCompleta() {
		return receitaRepository.findAll();
	}
	
	public void excluirReceita (Long id) {
		receitaRepository.deleteById(id);
	}
	
	public void popularBanco() {
		Receita receita = new Receita();
		
		receita.setModoDePreparo("Num copo americano baixo, coloque a rodela de laranja e adicione bastante gelo. Junte o gim, o Campari e o vermute tinto. Com uma colher bailarina, misture agitando o líquido de cima para baixo. Sirva a seguir.");
		receita.setNome("Negroni");
		receita.setTempoDePreparo(2);
		receita.setAlcoolico(true);
		receita.setInfo("1;Dose;Gin;1;Dose;Campari;1;Dose;Vermute tinto;1;Rodela;Laranja;5;Cubos;Gelo");
		
		salvarReceita(receita);
		
		Receita receita2 = new Receita();
		
		receita2.setModoDePreparo("Corte as extremidades do limão, coloque o limão com casca no liquidificador. Bata até ralar o limão com a casca, adicionando toda água gelada. Adoce com açúcar ou adoçante, bata mais um pouco e coe.");
		receita2.setNome("Limonada");
		receita2.setTempoDePreparo(5);
		receita2.setAlcoolico(false);
		receita2.setInfo("1;Todo;Limão;1;Litro;Água gelada;10;Gramas;Açucar ou adoçante;5;Cubos;Gelo");
		
		salvarReceita(receita2);
		
		Receita receita3 = new Receita();
		
		receita3.setModoDePreparo("Esfregue a fatia de limão na borda de uma taça, coloque sal espalhado em um prato e encoste a borda da taça no prato para fazer a crosta de sal na taça. Coloque em uma coqueteleira o suco de limão, licor, tequila e cubos de gelo. Agite bem e despeje na taça eliminando as pedras de gelo. Sirva.");
		receita3.setNome("Margarita");
		receita3.setTempoDePreparo(10);
		receita3.setAlcoolico(true);
		receita3.setInfo("1;Colher;Sal Marinho;2;Colheres;Cointreau;3;Colheres;Suco de Limão;1;Fatia;Limão;1;Dose;Tequila;3;Cubos;Gelo");
		
		salvarReceita(receita3);
		
	}
	
}
