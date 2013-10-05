import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Parser {
	ArrayList<String> handles= new ArrayList<String>();
	ArrayList<String> names= new ArrayList<String>();
	ArrayList<String> links = new ArrayList<String>();
	ArrayList<String> picLinks= new ArrayList<String>();
	public static String name;
	public Parser(){
		try{

			Document doc  = Jsoup.connect("http://twitaholic.com/").get();
			Elements td = doc.select("td"); 
			for(Element e: td){
				if (e.html().contains("(aka ")){
					//100 is the arbitrary limit for number of celeb links to parse 
					String theirName = e.html().substring(e.html().indexOf("title")+7, e.html().indexOf(" ("));
					String theirHandle= e.html().substring( e.html().indexOf("(aka "), e.html().indexOf(")"));
					String name =theirHandle.substring(5);
					handles.add("https://www.twitter.com/"+name);
					names.add(theirName);
				}
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	public String getImage(){
		String newRef=null;
		Boolean again=true;
		String[] celeb;
		Document doc;
		try{
			doc=null;
			while (again){
				celeb=getCelebLinks().split("\\s\\s\\s\\s\\s\\s");
				String celeblink=celeb[0];
				doc  = Jsoup.connect(celeblink).get();
				Elements urls = doc.select("[href]"); 
				for(Element e: urls){
					String absHref = e.attr("abs:href"); 
					//URL.add(absHref);
					if (absHref.contains("t.co")){

						String ref = absHref; 
						links.add(ref);
					}
				}
				if (links.size()!=0){
				for (String l: links){
					try {
						Document secondDoc= Jsoup.connect(l).get();
						Elements secondUrls= secondDoc.select("img");
						int i=0;
						for (Element f: secondUrls){
							String newHref= f.attr("src");

							if (newHref.contains(".jpg")){
								if (i <50){
									i++;
									newRef= newHref;
									picLinks.add(newRef);
								}
							}
						}

						if (picLinks.size()==0){
							again=true;
						}
						else{
							again=false;
							int index=(int) (Math.random() *picLinks.size()) ;
							
							return  picLinks.get(index);

						}		
					}
					catch (HttpStatusException e){
						System.out.println("error lets try another celeb!");
						again=true;
					}
				}
				}
			}
			//String f=e.html();

			//return URL;
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return newRef;
	}
	

	//THIS PORTION IS IF YOU WANT TO GET THE PROFILE PICTURE OF THE TWEETER
	/*	for (Element g: thirdUrls){
					String newHref= g.attr("src");
					if (newHref.contains(".jpg") && !newHref.contains("profile")
							&& newHref.contains("http"))
					{
						String newRef= newHref;
						picLinks.add(newRef);
					}
				}*/
	//String f=e.html();

	//return URL;


	public String getCelebLinks(){
		// this gets the top 100 tweeters according tweetaholic then stores a list of their handles

		// this gets the names and the handles of the most popular tweeters 
		int getRandom= (int)((Math.random())*handles.size());
		String celebrity= handles.get(getRandom);
		 name= names.get(getRandom);
		System.out.println("The tweeter name is: " + name);
		//this gets the handle and the name 
		return celebrity ;

	}
}
