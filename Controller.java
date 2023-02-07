package com.ty.Poc.Controller;

import java.io.IOException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.security.GeneralSecurityException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ty.Poc.Dao.Dto;
import com.ty.Poc.Dao.GoogleSheetDto;
import com.ty.Poc.Dao.GoogleSheetresponseDTO;
import com.ty.Poc.Service.GoogleApiService;

import jakarta.annotation.PostConstruct;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@org.springframework.stereotype.Controller
public class Controller {

	@Autowired
	GoogleApiService apiService;

	@GetMapping("/user")
	public String dataFromSheet(Model model) throws GeneralSecurityException, IOException {
		model.addAttribute("user", apiService.readDataFromGoogleSheet());
		return "user";
	}
	
	@GetMapping("/user/new")
	public String createSheet(Model model) {
		GoogleSheetDto dto= new GoogleSheetDto();
		model.addAttribute("user", dto);
		return "create_user";
	}

	@PostMapping("/user")
	public String createGoogleSheet(@ModelAttribute("user") GoogleSheetDto dto)
			throws GeneralSecurityException, IOException {
		 apiService.creategoogleSheet(dto);
		 return "redirect:/user";
	}
}
