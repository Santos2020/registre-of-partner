package br.com.registre.of.partner.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.registre.of.partner.converter.DozerConverter;
import br.com.registre.of.partner.data.model.Usuario;
import br.com.registre.of.partner.data.vo.V2.UsuarioVO;
import br.com.registre.of.partner.exception.ResourceNotFoundException;
import br.com.registre.of.partner.repository.UsuarioRepository;

@Service
public class UsuarioServices {
	
	@Autowired
	
	UsuarioRepository repository;
	

		
	public UsuarioVO create(UsuarioVO usuario) {
		var entity = DozerConverter.parseObject(usuario, Usuario.class);
		var vo = DozerConverter.parseObject(repository.save(entity), UsuarioVO.class);
		return vo;
	}

	
	public List<UsuarioVO> findAll() {
		return DozerConverter.parseListObjects(repository.findAll(), UsuarioVO.class);
	}	
	
	public UsuarioVO findById(Long id) {

		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		return DozerConverter.parseObject(entity, UsuarioVO.class);
	}
		
	public UsuarioVO update(UsuarioVO usuario) {
		var entity = repository.findById(usuario.getKey())
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		
		entity.setCodigo(usuario.getCodigo());
		entity.setSenha(usuario.getSenha());
		
		
		var vo = DozerConverter.parseObject(repository.save(entity), UsuarioVO.class);
		return vo;
	}	
	
	public void delete(Long id) {
		Usuario entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		repository.delete(entity);
	}

}
