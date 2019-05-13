package mcleod.programfinal.model;

import java.util.Observable;
import java.util.Observer;

public class SectionObserver implements Observer{

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		System.err.println("Error: Section at capacity limit. unable to add student: comma comma!");
	}

}
