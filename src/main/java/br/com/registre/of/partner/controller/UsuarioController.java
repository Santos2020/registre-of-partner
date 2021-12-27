package br.com.registre.of.partner.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.registre.of.partner.data.vo.V2.UsuarioVO;
import br.com.registre.of.partner.services.UsuarioServices;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;



@Api(tags = "UsuarioEndpoint") 
@RestController
@RequestMapping("/api/usuario/v1")
public class UsuarioController {
	
	@Autowired
	private UsuarioServices service;
	
	@ApiOperation(value = "Find all usuario" )
	@GetMapping(produces = { "application/json", "application/xml", "application/x-yaml" })
	public List<UsuarioVO> findAll() {
		List<UsuarioVO> usuarios =  service.findAll();
		usuarios
			.stream()
			.forEach(p -> p.add(
					linkTo(methodOn(UsuarioController.class).findById(p.getKey())).withSelfRel()
				)
			);
		return usuarios;
	}	
	
	@ApiOperation(value = "Find a specific usuario by your ID" )
	@GetMapping(value = "/{id}", produces = { "application/json", "application/xml", "application/x-yaml" })
	public UsuarioVO findById(@PathVariable("id") Long id) {
		UsuarioVO usuarioVO = service.findById(id);
		usuarioVO.add(linkTo(methodOn(UsuarioController.class).findById(id)).withSelfRel());
		return usuarioVO;
	}	
	
	@ApiOperation(value = "Create a new usuario")
	@PostMapping(produces = { "application/json", "application/xml", "application/x-yaml" }, 
			consumes = { "application/json", "application/xml", "application/x-yaml" })
	public UsuarioVO create(@RequestBody UsuarioVO usuario) {
		UsuarioVO usuarioVO = service.create(usuario);
		usuarioVO.add(linkTo(methodOn(UsuarioController.class).findById(usuarioVO.getKey())).withSelfRel());
		return usuarioVO;
	}
	
	@ApiOperation(value = "Update a specific ususario")
	@PutMapping(produces = { "application/json", "application/xml", "application/x-yaml" }, 
			consumes = { "application/json", "application/xml", "application/x-yaml" })
	public UsuarioVO update(@RequestBody UsuarioVO usuario) {
		UsuarioVO UsuarioVO = service.update(usuario);
		UsuarioVO.add(linkTo(methodOn(UsuarioController.class).findById(UsuarioVO.getKey())).withSelfRel());
		return usuario;
	}	
	
	@ApiOperation(value = "Delete a specific usuario by your ID")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		service.delete(id);
		return ResponseEntity.ok().build();
	}	
	
}