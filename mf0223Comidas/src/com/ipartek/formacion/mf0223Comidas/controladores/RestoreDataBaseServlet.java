package com.ipartek.formacion.mf0223Comidas.controladores;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.ipartek.formacion.mf0223Comidas.entidades.Alerta;

import lombok.extern.java.Log;

/**
 * Muestra la JSP archivo.jsp para seleccionar el archivo (.sql) con el archivo
 * con las sentencias de MySql para hacer el restore de la BB.DD
 * 
 * @author Jaime Quintana
 * @version 1.0
 */
@Log
@WebServlet("/restore")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
public class RestoreDataBaseServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher(Config.PATH_VISTAS + "archivo.jsp").forward(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Cambiar codificación de entrada de datos de formulario de Windows-1252 a UTF8
		try {
			request.setCharacterEncoding("utf-8");
		} catch (Exception e) {
			e.printStackTrace();
		}

		String uploadPath = getServletContext().getRealPath("") + File.separator + Config.UPLOAD_DIRECTORY;
		log.info(uploadPath);

		// Si no existe el directorio, lo creamos
		File uploadDir = new File(uploadPath);
		if (!uploadDir.exists())
			uploadDir.mkdir();

		String nombreFichero = null;
		String fileRestore = "";

		try {
			for (Part part : request.getParts()) {
				nombreFichero = part.getSubmittedFileName();

				log.info("Nombre de fichero: [" + nombreFichero + "]");

				if (nombreFichero != null && nombreFichero.trim().length() > 0) {
					log.info("Nombre de fichero ACEPTADO: [" + nombreFichero + "]");
					fileRestore = uploadPath + File.separator + nombreFichero;

					log.info(fileRestore);
					part.write(uploadPath + File.separator + nombreFichero);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Restore de la BBDD
		Config.dataBase.restaurarBaseDeDatos(fileRestore);

		// 5. Redirigir a otra vista
		String texto = "Restore de la BB.DD efectuado correctamente";
		Alerta alerta = new Alerta("success", texto);
		request.setAttribute("alerta", alerta);

		//
		try {
			Thread.sleep(3000);
		} catch (Exception e) {
			e.printStackTrace();
		}

		request.getRequestDispatcher("/index").forward(request, response);
	}

}