package com.uniovi.sdi;
import java.util.LinkedList;
import java.util.List;
import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
public class ProductosService {
<<<<<<< HEAD
	
=======
>>>>>>> branch 'master' of https://github.com/UO247346/sdi1920-506-lab-jee.git
	public List<Producto> getProductos(){
		List<Producto> productos = new LinkedList<Producto>();
		ObjectContainer db = null;
		try {
			db = Db4oEmbedded.openFile("bdProductos");
			List<Producto> respuesta = db.queryByExample(Producto.class);
			// NO RETORNAR LA MISMA LISTA DE LA RESPUESTA
			productos.addAll(respuesta);

		} finally {
			db.close();
		}
		return productos;
	}

	public void setNuevoProducto(Producto nuevoProducto){
		ObjectContainer db = null;
		try {
			db = Db4oEmbedded.openFile("bdProductos");
			db.store(nuevoProducto);
		} finally {
			db.close();
		}
	}
}