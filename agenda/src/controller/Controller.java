package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import model.DAO;
import model.JavaBeans;

@WebServlet(urlPatterns = { "/Controller", "/main", "/insert", "/select", "/update", "/delete", "/report"})
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DAO dao = new DAO();
	JavaBeans contato = new JavaBeans();

	public Controller() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();
		if (action.equals("/main")) {
			contatos(request, response);
		} else if (action.equals("/insert")) {
			novoContato(request, response);
		} else if (action.equals("/select")) {
			listarContato(request, response);
		} else if (action.equals("/update")) {
			editarContato(request, response);
		}else if (action.equals("/delete")) {
			deletarContato(request, response);
		}else if(action.equals("/report")) {
			criarRelatorio(request,response);
		}else {
			response.sendRedirect("index.html");
		}
	}

	// Listar Contatos

	protected void contatos(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Criando objeto que retorna os dados de JavaBeans
		ArrayList<JavaBeans> lista = dao.listarContato();
		// Encaminhar a lista do banco de dados para agenda.jsp
		request.setAttribute("contatos", lista);
		RequestDispatcher rd = request.getRequestDispatcher("agenda.jsp");
		rd.forward(request, response);
	}

	// Criar contatos

	protected void novoContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		contato.setNome(request.getParameter("nome"));
		contato.setFone(request.getParameter("fone"));
		contato.setEmail(request.getParameter("email"));
		// Teste do recebimento dos dados
		System.out.println(request.getParameter("nome"));
		System.out.println(request.getParameter("fone"));
		System.out.println(request.getParameter("email"));

		// Invocando método inserirContato passando o objeto contato
		dao.inserirContato(contato);
		// Redirecionar para o documento agenda.jsp

		response.sendRedirect("main");
	}

	protected void listarContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Recebendo ID do contato a ser editado
		String idcon = request.getParameter("idcon");
		contato.setIdCon(idcon);
		System.out.println(idcon);
		dao.selecionarContato(contato);
		// teste de recebimento
		System.out.println(contato.getIdCon());
		System.out.println(contato.getNome());
		System.out.println(contato.getFone());
		System.out.println(contato.getEmail());
		// Setando os atributos da classe JavaBeans na table

		request.setAttribute("id", contato.getIdCon());
		request.setAttribute("nome", contato.getNome());
		request.setAttribute("fone", contato.getFone());
		request.setAttribute("email", contato.getEmail());

		// encaminhar dados ao documento editar.jsp

		RequestDispatcher rd = request.getRequestDispatcher("editar.jsp");
		rd.forward(request, response);
	}
	
	protected void editarContato(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		contato.setIdCon(request.getParameter("id"));
		contato.setNome(request.getParameter("nome"));
		contato.setFone(request.getParameter("fone"));
		contato.setEmail(request.getParameter("email"));
		//
		dao.editarContato(contato);
		//
		response.sendRedirect("main");
	}
	
	protected void deletarContato(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("idcon");
		System.out.println(id);
		contato.setIdCon(id);
		
		dao.deletarContato(contato);
		
		response.sendRedirect("main");
	}
	
	protected void criarRelatorio(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		System.out.println("Reportado");
		Document documento = new Document();
		try {
			// tipo do conteúdo
			response.setContentType("apllication/pdf");
			// nome do documento
			response.setHeader("Content-Disposition", "inline;filename=" + "contatos.pdf");
			PdfWriter.getInstance(documento, response.getOutputStream());
			// abrir o documento
			documento.open();
			documento.add(new Paragraph("Lista de contatos:"));
			documento.add(new Paragraph(" \n "));
			// criando tabela
			PdfPTable tablePDF = new PdfPTable(4);
			
			// cabecalho
			PdfPCell col0 = new PdfPCell(new Paragraph("ID"));
			PdfPCell col1 = new PdfPCell(new Paragraph("Nome"));
			PdfPCell col2 = new PdfPCell(new Paragraph("Fone"));
			PdfPCell col3 = new PdfPCell(new Paragraph("Email"));
			tablePDF.addCell(col0);
			tablePDF.addCell(col1);
			tablePDF.addCell(col2);
			tablePDF.addCell(col3);
			
			// Prenchendo a tabela com contatos
			
			ArrayList<JavaBeans> lista = dao.listarContato();
			for (int i = 0; i < lista.size(); i++) {
				tablePDF.addCell(lista.get(i).getIdCon());
				tablePDF.addCell(lista.get(i).getNome());
				tablePDF.addCell(lista.get(i).getFone());
				tablePDF.addCell(lista.get(i).getEmail());
			}
			documento.add(tablePDF);
			documento.close();
		} catch (Exception e) {
			System.out.println(e);
			documento.close();
		}
		response.sendRedirect("main");
	}
}
