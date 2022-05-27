package com.arthur.helpdesk.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.arthur.helpdesk.domain.Pessoa;
import com.arthur.helpdesk.domain.Tecnico;
import com.arthur.helpdesk.domain.dtos.TecnicoDTO;
import com.arthur.helpdesk.repositories.PessoaRepository;
import com.arthur.helpdesk.repositories.TecnicoRepository;
import com.arthur.helpdesk.services.excepitions.DataIntegrityViolationException;
import com.arthur.helpdesk.services.excepitions.ObjectnotFoundException;

@Service
public class TecnicoService {

	@Autowired
	private TecnicoRepository repository;
	@Autowired
	private PessoaRepository pessoaRepository;
	@Autowired
	private  BCryptPasswordEncoder encoder;
	
	public Tecnico findById(Integer id) {
		Optional<Tecnico> obj = repository.findById(id);
		 return obj.orElseThrow(() -> new ObjectnotFoundException("Objeto não encontrado! Id:" + id));
		
	}

	public List<Tecnico> findAll() {
		return repository.findAll();
	}

	public Tecnico create(TecnicoDTO objDTO) {
		objDTO.setId(null);
		objDTO.setSenha(encoder.encode(objDTO.getSenha()));
		validaPorCpfEmail(objDTO);
	    Tecnico newObj = new Tecnico(objDTO);
	    return repository.save(newObj);
	}
	public Tecnico update(Integer id, @Valid TecnicoDTO objDTO) {
		objDTO.setId(id);
		Tecnico oldObj = findById(id);
		validaPorCpfEmail(objDTO);
		oldObj = new Tecnico(objDTO);
		return repository.save(oldObj);
	}
    public void delete(Integer id) {
	Tecnico obj = findById(id);
	if(obj.getChamados().size() > 0) {
		throw new DataIntegrityViolationException("Tecnico possui ordens de serviço e não pode ser deletado!");
	  }
		  repository.deleteById(id);
	}


	private void validaPorCpfEmail(TecnicoDTO objDTO) {
        Optional<Pessoa> obj =  pessoaRepository.findByCpf(objDTO.getCpf());
        if(obj.isPresent() && obj.get().getId() != objDTO.getId()) {
        	throw new DataIntegrityViolationException("CPF JÁ CADASTRADO NO SISTEMA");
        }
        
        obj = pessoaRepository.findByEmail(objDTO.getEmail());
        if(obj.isPresent() && obj.get().getId() != objDTO.getId()) {
        	throw new DataIntegrityViolationException("E-mail JÁ CADASTRADO NO SISTEMA");
        }
        
	}

	
	
}

