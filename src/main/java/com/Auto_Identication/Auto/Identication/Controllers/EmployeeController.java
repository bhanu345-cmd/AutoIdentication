package com.Auto_Identication.Auto.Identication.Controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.Auto_Identication.Auto.Identication.Dao.EmployeeDao;
import com.Auto_Identication.Auto.Identication.Dao.LoanCustomerDao;
import com.Auto_Identication.Auto.Identication.Models.BankEmployee;
import com.Auto_Identication.Auto.Identication.Models.BankEmployeeLogin;
import com.Auto_Identication.Auto.Identication.Models.LoanCustomer;
import com.Auto_Identication.Auto.Identication.Services.EmployeeServices;

@Controller
@RequestMapping(value="/emp")
public class EmployeeController 
{
	@Autowired
	private EmployeeServices employeeservices;
	@Autowired
	private LoanCustomerDao loandao;
	
	@GetMapping("/")
public String empLogin(Model model)
{
		BankEmployeeLogin bankemployeelogin=new BankEmployeeLogin();
		model.addAttribute("bankemployeelogin", bankemployeelogin);
	return "login";
}
	@PostMapping("/verifylogin")
public String empLoginVerify(@ModelAttribute("bankemployeelogin") BankEmployeeLogin bel,Model model,HttpSession session)
{
		session.setAttribute("userid", bel.getUserId());
		int res=employeeservices.getBankEmployee(bel);
		if(res==1)
		{
			
			return "emphome";
		}
		else if(res==2)
		{
			model.addAttribute("message","please wait for admin approval");
			return "login";
		}
		else if(res==3)
		{
			model.addAttribute("message", "please check thye details");
			return "login";
		}
		model.addAttribute("message","you are not registered please regsiter");
	return "login";
}
	@GetMapping("/register")
public String empRegister(Model empmodel)
{
		BankEmployee bankemployee=new BankEmployee();
		empmodel.addAttribute("bankemployee", bankemployee);
	return "registration";	
}
	@PostMapping("/verifyregister")
public String verifyEmpRegister(@ModelAttribute("bankemployee") BankEmployee be,Model model)
{
		
		be.setStatus("deactivate");
	int res=employeeservices.storeEmployee(be);
	BankEmployeeLogin bankemployeelogin=new BankEmployeeLogin();
	model.addAttribute("bankemployeelogin", bankemployeelogin);
	if(res==1)
	{
		model.addAttribute("message","you are succesfully registered" );
		return "login";
	}
	else if(res==2)
	{
		model.addAttribute("message", "you are already registered");
		return "login";
	}
	else
	{
		model.addAttribute("message", "something went wrong");
	return "login";
	}
}
	@GetMapping("/defaultlist")
	public String defaulterList(Model model)
	{
		List<LoanCustomer> cl=employeeservices.customerlist();
		List<LoanCustomer> customers=new ArrayList<LoanCustomer>();
		for (LoanCustomer loancustomer : cl)
		{
			if(loancustomer.getStatus().equals("defaulter"))
			{
			customers.add(loancustomer);
			}
		}
		model.addAttribute("custlist",customers);
		return "employeeworkforcustomer";
	}

	@GetMapping("/homeemp")
	public String empHome(Model model)
	{
		model.addAttribute("message","* Please logout at the end of the day *");
	    
		return "emphome";
	}
	
	@GetMapping("/details")
	public String empDetails(@RequestParam("id") int res,Model cusmodel,Model model,HttpSession session)
	{
		session.setAttribute("refer", res);
		LoanCustomer lc=loandao.findByaccountNumber(res);
		System.out.println(lc);
		cusmodel.addAttribute("customer",lc);
		model.addAttribute("card",lc.getCard());
		return "customerdueverify";
	}
	@GetMapping("/duelessthree")
	public String duelistlessthree(Model model)
	{
		List<LoanCustomer> list3=employeeservices.customerlist();
		List<LoanCustomer> dt=new ArrayList<LoanCustomer>();
		for (LoanCustomer dues : list3)
		{
			if(dues.getDues()<3)
			{
				dt.add(dues);
			}
			
		}
		System.out.println(dt);
		model.addAttribute("custlist",dt);
		return "employeeworkforcustomer";
	}
	@GetMapping("/duelesssix")
	public String duelistlesssix(Model model)
	{
		List<LoanCustomer> list6=employeeservices.customerlist();
		List<LoanCustomer> ds=new ArrayList<LoanCustomer>();
		for (LoanCustomer due : list6)
		{
			if(due.getDues()>=3 && due.getDues()<6 )
			{
				ds.add(due);
			}
			
		}
		model.addAttribute("custlist",ds);
		return "employeeworkforcustomer";
	}
	@GetMapping("/duelesstwelve")
	public String duelistlesstwelve(Model model)
	{
		List<LoanCustomer> list12=employeeservices.customerlist();
		List<LoanCustomer> dtw=new ArrayList<LoanCustomer>();
		for (LoanCustomer du : list12)
		{
			if(du.getDues()>=6 && du.getDues()<12)
			{
				dtw.add(du);
			}
			
		}
		model.addAttribute("custlist",dtw);
		return "employeeworkforcustomer";
	}
	@GetMapping("/duegreatertwelve")
	public String duelistgreatertwelve(Model model)
	{
		List<LoanCustomer> list13=employeeservices.customerlist();
		List<LoanCustomer> dth=new ArrayList<LoanCustomer>();
		for (LoanCustomer d : list13)
		{
			if(d.getDues()>=12)
			{
				dth.add(d);
			}
			
		}
		model.addAttribute("custlist",dth);
		return "employeeworkforcustomer";
	}
	@GetMapping("/duelist")
	public String duelist(Model model)
	{
		List<LoanCustomer> list=employeeservices.customerlist();
		List<LoanCustomer> l=new ArrayList<LoanCustomer>();
		for (LoanCustomer lc : list)
		{
			if(lc.getDues()<=3)
			{
			model.addAttribute("message","Late payment charges are applied");
			
			}
			else if(lc.getDues()>3 && lc.getDues()<=6)
			{
				model.addAttribute("message","Remainder sent");
				
			}
			else if(lc.getDues()>6 && lc.getDues() <12)
			{
				model.addAttribute("message","card to be blocked");
				
			}
			else
			{
				model.addAttribute("message","card was cancelled");
				
			}
		}
		return "employeeworkfromcustomer";
		
	}	

	
	
	@GetMapping("/cards")
	public String customerCards(Model model)
	{
		return "search";
	}
	@PostMapping("/getCard")
	public String searchCustomer(@RequestParam("number") int res,Model model,HttpSession session)
	{
session.setAttribute("account", res);
		LoanCustomer lc=loandao.findByaccountNumber(res);	
		if(lc==null)
		{
			model.addAttribute("message", "There is no customer");
			return "emphome";
		}
		model.addAttribute("card",lc.getCard());
		return "search";
	}
	@GetMapping("/reactive")
	public String reActivate(@RequestParam("reason") String rs,Model model,HttpSession session)
	{
		int res=(int) session.getAttribute("account");
		LoanCustomer lc=loandao.findByaccountNumber(res);
		if(lc.getCard().getCardStatus().equals("deactive"))
		{
			lc.getCard().setCardStatus("Re-active");
			lc.getCard().setReActivationReason(rs);
			LoanCustomer cardloan=loandao.save(lc);
			if(cardloan!=null)
			{
				model.addAttribute("message","card is re-cativated");
				return "emphome";
			}
			else
			{
				model.addAttribute("message","card is not re-cativated");
				return "emphome";
			}
		}
		model.addAttribute("message","already in re-actived");
		return "emphome";
	}
	@GetMapping("/charges")
	public String applyCharges(@RequestParam("id") int res,Model model)
	{
		LoanCustomer lc=loandao.findByaccountNumber(res);
		double f=lc.getFine();
		double vf=100*lc.getDues();
		if(f==vf)
		{
			model.addAttribute("message",lc.getFine()+"already charges applied");
			return "emphome";
		}
		else
		{
			lc.setFine(vf);
			LoanCustomer sl=loandao.save(lc);
			if(sl==null)
			{
				model.addAttribute("message","charges not applied");
				return "emphome";
			}
			else
			{
				model.addAttribute("message","charges applied");
				return "emphome";
			}
		}

	}	
	
	@GetMapping("/alert")
	public String message(Model model)
	{
		model.addAttribute("message","message sent succesfully");
		return "emphome";
	}
	@GetMapping("/pay")
	public String payAmount(Model model)
	{
		return "moneyPay";
	}
	
	@GetMapping("/verPay")
	public String verifyAmount(@RequestParam("amount") int amm,Model model,HttpSession session)
	{
		System.out.println(amm);
	int res=(int)session.getAttribute("refer");
	LoanCustomer lc=loandao.findByaccountNumber(res);
	int paid=lc.getMoneyPaid()+amm;
	lc.setMoneyPaid(paid);
	LoanCustomer loan=loandao.save(lc);
	if(loan!=null)
	{
		model.addAttribute("message", amm+" is successfully paid");
		return "emphome";
	}
	else
	{
		model.addAttribute("message", "something went wrong");
		return "emphome";
	}
	
	}
	
	
	
	
	
	
	
	
	

}
	


