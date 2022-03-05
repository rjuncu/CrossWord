package project;

public class CrosswordApp {

	public static void main(String[] args) throws Exception {
		GetPropertyValues prop = new GetPropertyValues();
		SpecialFileReader read = new SpecialFileReader(prop.getFileName());
		MakePuzzle puzzle = new MakePuzzle(read.getList(), read.getClues(), prop.getSolution());
	}
}

