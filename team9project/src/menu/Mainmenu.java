package menu;

public class Mainmenu {

	public static void main(String[] args) {
		Fileview view = new Fileview();
		Filemodel model = new Filemodel();
	    new Filecontroller(view, model);
	}

}
