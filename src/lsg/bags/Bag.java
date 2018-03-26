package lsg.bags;

import java.util.HashSet;
import java.util.Iterator;

import lsg.LearningSoulsGame;
import lsg.armor.BlackWitchVeil;
import lsg.armor.DragonSlayerLeggings;
import lsg.armor.RingedKnightArmor;
import lsg.consumables.food.Hamburger;
import lsg.weapons.Sword;

public class Bag {

	private int capacity; //capacité du sac
	private int weight; //nombre de kg utilises
	private HashSet<Collectible> items; 
	
	
	public Bag(int capacity) {
		this.items = new HashSet<>();
		this.capacity = capacity;
	}


	public int getCapacity() {
		return capacity;
	}

	public int getWeight() {
		return weight;
	}
	
	private void setWeight(int weight) {
		this.weight = weight;
	}
	
	
	/**
	 * méthode ajoutant un Collectible au sac si la place restante est suffisante
	 */
	public void push(Collectible item) {
		if (item.getWeight() <= this.getCapacity() - this.getWeight()) {
			this.items.add(item);
			int sommePoids = this.getWeight() + item.getWeight();
			this.setWeight(sommePoids);
		}	
	}
	
	/**
	 * méthode qui retire un item du sac
	 * @return Collectible
	 */
	public Collectible pop(Collectible item) {
		if (items.contains(item)) {
			items.remove(item);
			this.setWeight(this.getWeight() - item.getWeight());
			return item;
		}
		return null;
	}
	
	
	/**
	 * méthode qui indique si l'item passé en paramètre se trouve bien dans le sac
	 * @return boolean 
	 */
	public boolean contains(Collectible item) {
		if (items.contains(item)) {
			return true;
		}
		return false;
	}
	
	
	/**
	 * méthode retournant les items du sac
	 * @return Collectible[] 
	 */
	public Collectible[] getItems() {
		return items.toArray(new Collectible[items.size()]);
	}
	
	/**
	 * méthode affichant le contenu d'un sac
	 * @return String
	 */
	public String toString() {
		String affichage = "";
		if (this.getWeight() != 0) { //si le sac n'est pas vide
			affichage = String.format("%s [ %d items | %d/%d kg ]%s", this.getClass().getSimpleName(), this.items.size(), this.getWeight(), this.getCapacity(), System.lineSeparator());
			Iterator<Collectible> i = this.items.iterator();
			while(i.hasNext()) {
				Collectible collectible = i.next();
				affichage += String.format("%s %s [%d kg]%s", LearningSoulsGame.BULLET_POINT, collectible.toString(), collectible.getWeight(), System.lineSeparator());
			}
		}
		else {
			affichage = this.getClass().getSimpleName() + " [empty]" + System.lineSeparator();
		}
		return affichage;
	}
	
	/**
	 * méthode transférant le contenu du sac source vers le sac de destination (dans la limite de capacité de ce dernier)
	 */
	public static void transfer(Bag from, Bag into) {
		for(Collectible collectible : from.getItems()) {
			if (into.getCapacity() - into.getWeight() >= collectible.getWeight()) {
				into.push(collectible);
				from.pop(collectible);
			}
		}
	}
	
	public static void main (String[] args) {
		SmallBag pochetteChanel = new SmallBag();
		MediumBag sacMichaelKors = new MediumBag();
		BlackWitchVeil blackWitchVeil = new BlackWitchVeil();
		Hamburger hamburger = new Hamburger();
		Sword basicSword = new Sword();
		DragonSlayerLeggings dragonSlayerLeggings = new DragonSlayerLeggings();
		RingedKnightArmor ringedKnightArmor1 = new RingedKnightArmor();
		RingedKnightArmor ringedKnightArmor2 = new RingedKnightArmor();
		sacMichaelKors.push(blackWitchVeil);
		sacMichaelKors.push(hamburger);
		sacMichaelKors.push(basicSword);
		sacMichaelKors.push(dragonSlayerLeggings);
		sacMichaelKors.push(ringedKnightArmor1);
		sacMichaelKors.push(ringedKnightArmor2);
		
		
//		System.out.println(pochetteChanel.toString());
//		
//		System.out.println("Pop sur " + dragonSlayerLeggings.toString());
//		pochetteChanel.pop(dragonSlayerLeggings);
//		
//		System.out.println(pochetteChanel.toString());
//		
//		System.out.println("Push sur " + dragonSlayerLeggings.toString());
//		pochetteChanel.push(dragonSlayerLeggings);
//		
//		System.out.println(pochetteChanel.toString());
//		
//		System.out.println("getItems sur " + dragonSlayerLeggings.toString());
//		
//		System.out.println(pochetteChanel.getItems().length);
//		
//		System.out.println("Contains sur " + dragonSlayerLeggings.toString());
//		
//		System.out.println(pochetteChanel.contains(basicSword));
//		pochetteChanel.pop(dragonSlayerLeggings);
//		System.out.println(pochetteChanel.contains(dragonSlayerLeggings));
		
		System.out.println(sacMichaelKors.toString());
		System.out.println(pochetteChanel.toString());
		
		transfer(sacMichaelKors, pochetteChanel);
		System.out.println("Après transfert :" + System.lineSeparator()); 
		System.out.println(pochetteChanel.toString());
		System.out.println(sacMichaelKors.toString());
	}
}