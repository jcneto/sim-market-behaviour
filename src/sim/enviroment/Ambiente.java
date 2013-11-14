package sim.enviroment;

import java.util.ArrayList;
import java.util.List;

import app.localization.Point;

public abstract class Ambiente {

	public static int limiteX = 27;
	public static int limiteY = 49;

	public static List<Point> pontosFixos;
	public static Point pontoPegaCarrinhos;
	public static Point pontoInicialClientes;
	public static Point pontoSairSistema;

	// Iniciliazação
	static {

		pontosFixos = new ArrayList<Point>();
		pontoPegaCarrinhos = new Point(25, 4);
		pontoInicialClientes = new Point(1, 1);
		pontoSairSistema = new Point(1, 1);
	}

	public static boolean isLivre(Point pt) {
		return !pontosFixos.contains(pt);			
	}

}
