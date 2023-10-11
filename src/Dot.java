/*		08.10.2023		*/
public class Dot{

	public Dot(int x, int y){
		this.x = x;
		this.y = y;

	}

	private int x, y;
	public int getX(){
		return this.x;
	}
	public int getY(){
		return this.y;
	}
	private static double distance(Dot a, Dot b){
		int xDistSqr = (a.getX() - b.getX())* (a.getX() - b.getX());
		int yDistSqr = (a.getY() - b.getY())* (a.getY() - b.getY());
		return Math.sqrt(xDistSqr +yDistSqr);
	}
	private static double distanceSqr(Dot a, Dot b){
		int xDistSqr = (a.getX() - b.getX())* (a.getX() - b.getX());
		int yDistSqr = (a.getY() - b.getY())* (a.getY() - b.getY());
		return xDistSqr +yDistSqr;
	}
	static void area(Dot a, Dot b, Dot c) {
		double abDist = distance(a, b);
		double bcDist = distance(b, c);
		double caDist = distance(c, a);
		double abDist2 = distanceSqr(a, b);
		double bcDist2 = distanceSqr(b, c);
		double caDist2 = distanceSqr(c, a);
		double p = (abDist + bcDist + caDist) / 2;
		double area = Math.sqrt(p * (p - abDist) * (p - bcDist) * (p - caDist));
		System.out.print(area);
	}
}

