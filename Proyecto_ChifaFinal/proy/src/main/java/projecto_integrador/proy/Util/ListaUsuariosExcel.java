package projecto_integrador.proy.Util;

import java.util.List;
import java.util.Map;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractXlsxView;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import projecto_integrador.proy.Model.Usuario;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

@Component
public class ListaUsuariosExcel extends AbstractXlsxView {

    @Override
    protected void buildExcelDocument(@NonNull Map<String, Object> model, 
                                      @NonNull Workbook workbook,
                                      @NonNull HttpServletRequest request,
                                      @NonNull HttpServletResponse response) throws Exception {
        try {
            response.setHeader("Content-Disposition", "attachment; filename=\"listado_usuarios.xlsx\"");
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

            Sheet hoja = workbook.createSheet("Usuarios");

            // Verificar la existencia del archivo de imagen
            File logoFile = new File("F:\\Proyecto_Chifa\\proy\\src\\main\\resources\\static\\Imagenes\\LogotipoX.png");
            if (!logoFile.exists()) {
                System.out.println("Error: Archivo de imagen no encontrado en la ruta especificada: " + logoFile.getAbsolutePath());
                throw new FileNotFoundException("El archivo de imagen no se encuentra en la ruta especificada: " + logoFile.getAbsolutePath());
            }

            System.out.println("Cargando imagen desde: " + logoFile.getAbsolutePath());

            // Insertar el logo en la celda A1, ocupando de A1 a D5
            try (InputStream logoStream = new FileInputStream(logoFile)) {
                byte[] logoBytes = logoStream.readAllBytes();
                int pictureIdx = workbook.addPicture(logoBytes, Workbook.PICTURE_TYPE_PNG);
                Drawing<?> drawing = hoja.createDrawingPatriarch();
                CreationHelper helper = workbook.getCreationHelper();
                ClientAnchor anchor = helper.createClientAnchor();

                anchor.setCol1(0); // Columna A
                anchor.setRow1(0); // Fila 1
                anchor.setCol2(4); // Columna D (para que el logo se extienda hasta aquí)
                anchor.setRow2(5); // Fila 5 (para que el logo se extienda hasta aquí)

                drawing.createPicture(anchor, pictureIdx);
            }

            // Combinar y centrar el título en A7:D8
            Row tituloFila = hoja.createRow(6); // Fila 7 (en base 0)
            hoja.addMergedRegion(new CellRangeAddress(6, 7, 0, 3)); // Combina A7 a D8
            Cell tituloCelda = tituloFila.createCell(0);
            tituloCelda.setCellValue("Listado de Usuarios de Chifa de Loto");

            // Estilo del título
            CellStyle tituloStyle = workbook.createCellStyle();
            Font tituloFont = workbook.createFont();
            tituloFont.setFontHeightInPoints((short) 16);
            tituloFont.setBold(true);
            tituloStyle.setFont(tituloFont);
            tituloStyle.setAlignment(HorizontalAlignment.CENTER);
            tituloCelda.setCellStyle(tituloStyle);

            // Estilo de cabecera
            CellStyle headerStyle = workbook.createCellStyle();
            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerFont.setColor(IndexedColors.BLACK.getIndex()); // Color de fuente negro
            headerStyle.setFont(headerFont);
            headerStyle.setFillForegroundColor(IndexedColors.LIME.getIndex()); // Color verde limón
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            headerStyle.setAlignment(HorizontalAlignment.CENTER);
            headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            headerStyle.setBorderBottom(BorderStyle.THIN);
            headerStyle.setBorderTop(BorderStyle.THIN);
            headerStyle.setBorderRight(BorderStyle.THIN);
            headerStyle.setBorderLeft(BorderStyle.THIN);

            // Crear fila de cabecera de datos en A10:D10
            String[] columnas = {"ID", "Nombre", "Correo", "Contraseña"};
            Row filaCabecera = hoja.createRow(9); // Fila 10
            for (int i = 0; i < columnas.length; i++) {
                Cell cell = filaCabecera.createCell(i);
                cell.setCellValue(columnas[i]);
                cell.setCellStyle(headerStyle);
            }

            // Estilo para datos
            CellStyle dataStyle = workbook.createCellStyle();
            dataStyle.setBorderBottom(BorderStyle.THIN);
            dataStyle.setBorderTop(BorderStyle.THIN);
            dataStyle.setBorderRight(BorderStyle.THIN);
            dataStyle.setBorderLeft(BorderStyle.THIN);
            dataStyle.setAlignment(HorizontalAlignment.LEFT);
            dataStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            dataStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex()); // Color blanco para datos
            dataStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            dataStyle.setFont(getDefaultFont(workbook));

            // Obtener lista de usuarios
            @SuppressWarnings("unchecked")
            List<Usuario> usuarios = (List<Usuario>) model.get("usuarios");

            // Verificar usuarios
            int filaNum = 10; // Comienza en la fila 11 para los datos
            if (usuarios == null || usuarios.isEmpty()) {
                Row fila = hoja.createRow(filaNum);
                fila.createCell(0).setCellValue("No hay usuarios disponibles.");
                return;
            }

            // Crear filas de datos
            for (Usuario usuario : usuarios) {
                Row fila = hoja.createRow(filaNum++);
                Cell cellId = fila.createCell(0);
                cellId.setCellValue(usuario.getId());
                cellId.setCellStyle(dataStyle);

                Cell cellNombre = fila.createCell(1);
                cellNombre.setCellValue(usuario.getNombre());
                cellNombre.setCellStyle(dataStyle);

                Cell cellCorreo = fila.createCell(2);
                cellCorreo.setCellValue(usuario.getCorreo());
                cellCorreo.setCellStyle(dataStyle);

                Cell cellContrasena = fila.createCell(3);
                cellContrasena.setCellValue(usuario.getContrasena());
                cellContrasena.setCellStyle(dataStyle);
            }

            // Ancho fijo para columnas específicas
            hoja.setColumnWidth(1, 5000); // Nombre     
            hoja.setColumnWidth(2, 7000); // Correo
            hoja.setColumnWidth(3, 18000); // Contraseña
            hoja.autoSizeColumn(0); // ID

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    private Font getDefaultFont(Workbook workbook) {
        Font font = workbook.createFont();
        font.setFontHeightInPoints((short) 12);
        font.setColor(IndexedColors.BLACK.getIndex());
        return font;
    }
}
