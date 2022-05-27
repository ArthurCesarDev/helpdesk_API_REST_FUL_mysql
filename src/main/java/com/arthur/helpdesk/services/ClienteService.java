package com.arthur.helpdesk.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.arthur.helpdesk.domain.Pessoa;
import com.arthur.helpdesk.domain.Cliente;
import com.arthur.helpdesk.domain.dtos.ClienteDTO;
import com.arthur.helpdesk.repositories.PessoaRepository;
import com.arthur.helpdesk.repositories.ClienteRepository;
import com.arthur.helpdesk.services.excepitions.DataIntegrityViolationException;
import com.arthur.helpdesk.services.excepitions.ObjectnotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;
	@Autowired
	private PessoaRepository pessoaRepository;
	@Autowired
	private  BCryptPasswordEncoder encoder;
	
	public Cliente findById(Integer id) {
		Optional<Cliente> obj = repository.findById(id);
		 return obj.orElseThrow(() -> new ObjectnotFoundException("Objeto não encontrado! Id:" + id));
		
	}

	public List<Cliente> findAll() {
		return repository.findAll();
	}

	public Cliente create(ClienteDTO objDTO) {
		objDTO.setId(null);
		objDTO.setSenha(encoder.encode(objDTO.getSenha()));
		validaPorCpfEmail(objDTO);
	    Cliente newObj = new Cliente(objDTO);
	    return repository.save(newObj);
	}
	
	public Cliente update(Integer id, @Valid ClienteDTO objDTO) {
		objDTO.setId(id);
		Cliente oldObj = findById(id);
		validaPorCpfEmail(objDTO);
		oldObj = new Cliente(objDTO);
		return repository.save(oldObj);
	}
    public void delete(Integer id) {
	Cliente obj = findById(id);
	if(obj.getChamados().size() > 0) {
		throw new DataIntegrityViolationException("Cliente possui ordens de serviço e não pode ser deletado!");
	  }
		  repository.deleteById(id);
	}


	private void validaPorCpfEmail(ClienteDTO objDTO) {
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

