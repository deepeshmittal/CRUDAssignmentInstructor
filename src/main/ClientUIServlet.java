package main;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.jersey.api.client.ClientResponse;

import jaxb.xml.Converter;
import jaxb.xml.Student;
import jaxb.xml.Workitem;
/**
 * Servlet implementation class ClientUIServlet
 */
@WebServlet("/ClientUIServlet")
public class ClientUIServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
    public ClientUIServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		CRUDClient client = new CRUDClient();
		
		if(request.getParameter("task").equalsIgnoreCase("Add Student")){
			String name = request.getParameter("studentName");
			Student student = new Student();
			student.setName(name);
			String output = Converter.convertFromObjectToXml(student, Student.class);
			ClientResponse clientResponse = client.addStudent(output);
			int code = clientResponse.getStatus();
			String responseoutput = new String();
	    	if(code == 201){
	    		Student xml = clientResponse.getEntity(Student.class);
	    		responseoutput = "Http Code : "+ code + "<br>Student added successfully at location : " + clientResponse.getLocation() + "<br>Student ID: " + xml.getId();  
	    	}else{
	    		responseoutput = "Http Code : "+ code + "\n" + clientResponse.getStatusInfo();
	    	}
	    	request.setAttribute("message", responseoutput);
	    	request.getRequestDispatcher("/home.jsp").forward(request, response);
		}
		
		if(request.getParameter("task").equalsIgnoreCase("Add WorkItem")){
			String itemname = request.getParameter("workitemname");
			String percent = request.getParameter("percentage");
			Workitem item = new Workitem();
			item.setItemname(itemname);
			item.setPercent(percent);
			String output = Converter.convertFromObjectToXml(item, Workitem.class);
			ClientResponse clientResponse = client.addWorkItem(output);
			int code = clientResponse.getStatus();
			String responseoutput = new String();
	    	if(code == 201){
	    		responseoutput = "Http Code : "+ code + "<br>Workitem " + itemname + " added for all students"; 
	    	}else{
	    		responseoutput = "Http Code : "+ code + "<br>" + clientResponse.getStatusInfo();
	    	}
	    	request.setAttribute("message", responseoutput);
	    	request.getRequestDispatcher("/home.jsp").forward(request, response);
		}
		
		if(request.getParameter("task").equalsIgnoreCase("Add Grade")){
			String id = request.getParameter("studentID");
			String itemname = request.getParameter("assignID");				
			String grade = request.getParameter("Grade");
			String feedback = request.getParameter("feedback");
			
			Workitem item1 = new Workitem();
			item1.setGrades(grade);
			item1.setFeedback(feedback);
			String output = Converter.convertFromObjectToXml(item1, Workitem.class);
			ClientResponse clientResponse = client.addGrade(id, itemname, output);
			int code = clientResponse.getStatus();
			String responseoutput;
	    	if(code == 200){
	    		responseoutput = "Http Code : "+ code + "<br>Successfully Added " + itemname + " grades for student : " + id; 
	    	}else if(code == 404){
	    		responseoutput = "Http Code : "+ code + "<br>Student : " + id + " not found"; 
	    	}
	    	else if(code == 409){
	    		responseoutput = "Http Code : "+ code + "<br>Workitem : " + itemname + " not found for student : " + id; 
	    	}else{
	    		responseoutput = "Http Code : "+ code + "<br>" + clientResponse.getStatusInfo();
	    	}
	    	request.setAttribute("message", responseoutput);
	    	request.getRequestDispatcher("/home.jsp").forward(request, response);
		}
		
		if(request.getParameter("task").equalsIgnoreCase("Update Grade")){
			String id = request.getParameter("studentID");
			String itemname = request.getParameter("assignID");				
			String grade = request.getParameter("Grade");
			String feedback = request.getParameter("feedback");
			
			Workitem item1 = new Workitem();
			item1.setItemname(itemname);
			item1.setGrades(grade);
			item1.setFeedback(feedback);
			String output = Converter.convertFromObjectToXml(item1, Workitem.class);
			ClientResponse clientResponse = client.updateGrade(id,output);
			int code = clientResponse.getStatus();
			String responseoutput;
	    	if(code == 200){
	    		responseoutput = "Http Code : "+ code + "<br>Successfully updated " + itemname + " grades for student : " + id; 
	    	}else if(code == 404){
	    		responseoutput = "Http Code : "+ code + "<br>Student : " + id + " not found"; 
	    	}
	    	else if(code == 409){
	    		responseoutput = "Http Code : "+ code + "<br>Workitem : " + itemname + " not found for student : " + id; 
	    	}else{
	    		responseoutput = "Http Code : "+ code + "<br>" + clientResponse.getStatusInfo();
	    	}
	    	request.setAttribute("message", responseoutput);
	    	request.getRequestDispatcher("/home.jsp").forward(request, response);
		}	
		if(request.getParameter("task").equalsIgnoreCase("View Grade")){
			String id = request.getParameter("studentID");
			String itemname = request.getParameter("assignID");				
			if (itemname == ""){
				itemname = "NoAssign";
			}
			ClientResponse clientResponse = client.viewGrade(id,itemname);
			int code = clientResponse.getStatus();
			String responseoutput;
	    	if(code == 200){
	    		if(!itemname.equalsIgnoreCase("NoAssign")){
	    			Workitem xml = clientResponse.getEntity(Workitem.class);
		    		if(xml.getGrades() == null || xml.getGrades().equalsIgnoreCase("")){
		    			responseoutput = "Http Code : "+ code + "<br>Grade for " + "Student " + id + " and WorkItem " + itemname + " has not been posted yet.";
		    		}else
		    		responseoutput = "Http Code : "+ code + "<br>Student ID : " + id + "<br>WorkItem : " + itemname + "<br>Grade : " + xml.getGrades() + "<br>Feedback : " + xml.getFeedback();
	    		}else{
	    			Student xml = clientResponse.getEntity(Student.class);
	    			ArrayList<Workitem> itemlist = new ArrayList<Workitem>();
	    			itemlist = xml.getWorkitemlist();
	    			responseoutput = "Http Code : "+ code + "<br>Student : " + id;
	    			for(Workitem item:itemlist){
	    				if(item.getGrades() == null || item.getGrades().equalsIgnoreCase("")){
			    			responseoutput += " <br>Grade for WorkItem " + item.getItemname() + " has not been posted yet.";
			    		}else
			    		responseoutput += "<br>WorkItem : " + item.getItemname() + "<br>Grade : " + item.getGrades() + "<br>Feedback : " + item.getFeedback();	
	    			}	
	    		} 
	    	}else if(code == 404){
	    		responseoutput = "Http Code : "+ code + "<br>Student : " + id + " not found"; 
	    	}
	    	else if(code == 409){
	    		responseoutput = "Http Code : "+ code + "<br>Workitem : " + itemname + " not found for student : " + id; 
	    	}else{
	    		responseoutput = "Http Code : "+ code + "<br>" + clientResponse.getStatusInfo();
	    	}
	    	request.setAttribute("message", responseoutput);
	    	request.getRequestDispatcher("/home.jsp").forward(request, response);
		}	
		if(request.getParameter("task").equalsIgnoreCase("Delete Grade")){
			String id = request.getParameter("studentID");
			String itemname = request.getParameter("assignID");				
			if (itemname == ""){
				itemname = "NoAssign";
			}
			
			ClientResponse clientResponse = client.deleteGrade(id,itemname);
			int code = clientResponse.getStatus();
			String responseoutput;
	    	if(code == 204){
	    		if(!itemname.equalsIgnoreCase("NoAssign")){
	    			responseoutput = "Http Code : "+ code + "<br>Student ID : " + id + "<br>Grade for WorkItem " + itemname + " has been deleted";
		    			
		    	}else
		    		responseoutput = "Http Code : "+ code + "<br>Student ID : " + id + "<br>All workitem grades has been deleted.";
	    	}else if(code == 404){
	    		responseoutput = "Http Code : "+ code + "\nStudent : " + id + " not found"; 
	    	}
	    	else if(code == 409){
	    		responseoutput = "Http Code : "+ code + "\nWorkitem : " + itemname + " not found for student : " + id; 
	    	}else{
	    		responseoutput = "Http Code : "+ code + "\n" + clientResponse.getStatusInfo();
	    	}
	    	request.setAttribute("message", responseoutput);
	    	request.getRequestDispatcher("/home.jsp").forward(request, response);
		}	
    }
}
