import java.util.ArrayList;

// This class should get all the html links from a give 

public class ActorFeed {
	//ArrayList<Item> receipt; 
	ArrayList<String> links;
	ArrayList<String> names;
	
	int random;
	String pic, actualName;
	public ActorFeed(){
		toParser();
	}
	private void toParser(){
		Parser p = new Parser();
		String[] image=p.getImage().split("\\s\\s\\s\\s\\s\\s");
		for (int i =0; i < image.length; i++){
			System.out.println(image[i]);
		}
		 pic=image[2];
		 actualName=image[1];


	}
	public String getImages(){
		return pic;
	}
	public String getName(){
		return actualName;
	}


	//gets the links in arraylist

	//deletes the links
	public void removeAllLinks() {
		for (int i=0; i<links.size(); i++){
			links.remove(i);
		}
	}

}

