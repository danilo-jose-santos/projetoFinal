package br.com.isidrocorp.projeto.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.isidrocorp.projeto.dao.UsuarioDAO;
import br.com.isidrocorp.projeto.model.Usuario;

@RestController
public class UsuarioController {
	
	@Autowired				//injeção da dependência
	private UsuarioDAO dao;
	
	@GetMapping("/usuario")		// o /usuario será o caminho do browser
	public ArrayList<Usuario> listarTudo(){
		ArrayList<Usuario> lista = (ArrayList<Usuario>)dao.findAll();
		return lista;
	}
	
	/*
	@PostMapping("/login")
	public Usuario loginEmail(@RequestBody Usuario userEmailSenha) {
		Usuario user = dao.findByEmailAndSenha(userEmailSenha.getEmail(), userEmailSenha.getSenha());
		return user;
	}
	
	@PostMapping("/racf")
	public Usuario loginRacf(@RequestBody Usuario userRacfSenha) {
		Usuario racf = dao.findByRacfAndSenha(userRacfSenha.getRacf(), userRacfSenha.getSenha());
		return racf;
	}
	
	@PostMapping("/login2")
	public Usuario loginEmailRacf(@RequestBody Usuario incompleto) {
		if (incompleto.getEmail() != null) {
			Usuario user = dao.findByEmailAndSenha(incompleto.getEmail(), incompleto.getSenha());
			return user;	
		}
		else {
			Usuario racf = dao.findByRacfAndSenha(userRacfSenha.getRacf(), userRacfSenha.getSenha());
			return racf;
		}*/
	
	@PostMapping("/login")
	
	public ResponseEntity<Usuario> login(@RequestBody Usuario incompleto) {
		Usuario resultado = dao.findByRacfOrEmail(incompleto.getRacf(), incompleto.getEmail());
		if (resultado != null) {  // achei um usuario no banco!
			if (incompleto.getSenha().equals(resultado.getSenha())) { // as senhas coincidem??
				resultado.setSenha("*******");
				return ResponseEntity.ok(resultado);
			}
			else {
				return ResponseEntity.status(403).build(); // retorno "Forbidden"
			}
		}
		else {
			return ResponseEntity.notFound().build();   // retorno um status de "Não encontrado"
		}
	}
	}
