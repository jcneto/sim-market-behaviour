package app.localization;

import java.util.ArrayList;
import java.util.List;

public class Point {
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Point() {
		// TODO Auto-generated constructor stub
	}

	private int x;
	private int y;

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Point novoLocal(int x, int y) {
		return new Point(this.x + x, this.y + y);
	}

	@Override
	public String toString() {

		return "[" + this.x + "," + this.y + "]";
	}

	@Override
	public boolean equals(Object arg0) {
		Point ptAux = (Point) arg0;
		if ((ptAux.getX() == this.x) && (ptAux.getY() == this.y)) {
			return true;
		} else {
			return false;
		}

	}

	public List<Point> calcularAdjacentes(int raio) {
		List<Point> ptPossiveis = new ArrayList<Point>();

		for (int i = this.x - raio; i <= this.x + raio; i++) {
			for (int j = this.y - raio; j <= this.y + raio; j++) {
				ptPossiveis.add(new Point(i, j));
			}
		}
		return ptPossiveis;
	}

	public List<Point> calcularAdjacentes(boolean diagonais) {
		List<Point> ptPossiveis = new ArrayList<Point>();

		ptPossiveis.add(new Point(x - 1, y));

		ptPossiveis.add(new Point(x, y - 1));
		ptPossiveis.add(new Point(x, y));
		ptPossiveis.add(new Point(x, y + 1));

		ptPossiveis.add(new Point(x + 1, y));

		// Diagonais
		if (diagonais) {
			ptPossiveis.add(new Point(x - 1, y - 1));
			ptPossiveis.add(new Point(x - 1, y + 1));
			ptPossiveis.add(new Point(x + 1, y - 1));
			ptPossiveis.add(new Point(x + 1, y + 1));
		}
		return ptPossiveis;
	}

	@Override
	public Object clone() {
		Point pt = new Point(this.x, this.y);
		return pt;
	}

	public int calcularDiferenca(Point pt) {
		int difX = this.getX() - pt.getX();
		int difY = this.getY() - pt.getY();
		int difAgregada = (difX * Integer.signum(difX))
				+ (difY * Integer.signum(difY));

		return difAgregada;
	}
	
	@Override
	public int hashCode() {
		
		return x;
	}

}
