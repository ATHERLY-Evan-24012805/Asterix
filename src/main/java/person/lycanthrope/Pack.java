package person.lycanthrope;

import java.util.ArrayList;
import java.util.List;

public class Pack {

    private String name;
    private List<Lycanthrope> members;
    private Howl howl;

    private Lycanthrope alphaMale;
    private Lycanthrope alphaFemale;

    public Pack(String name, String howl, Lycanthrope alphaMale, Lycanthrope alphaFemale) {
        this.name = name;
        this.howl = new Howl(howl);
        this.alphaMale = alphaMale;
        this.alphaFemale = alphaFemale;
        this.members = new ArrayList<>();
    }

    // Ajout d'un lycanthrope
    public void addLycanthrope(Lycanthrope l) {

        if (!members.contains(l)) {
            members.add(l);
            l.joinPack(this, DominationRank.OMEGA);
            updateHierarchy();
        }
    }

    // Suppression d'un Lycanthrope
    public void removeLycanthrope(Lycanthrope l) {
        if (members.remove(l)) {
            l.leavePack();
            updateHierarchy();
        }
    }


    public Howl getHowl() {
        return howl;
    }

    // Création de la hierarchie permet initialisation propre
    private void updateHierarchy() {

        // Sécurise que le couple alpha est bien dans la meute
        if (alphaMale != null && !members.contains(alphaMale)) {
            members.add(alphaMale);
        }
        if (alphaFemale != null && !members.contains(alphaFemale)) {
            members.add(alphaFemale);
        }

        // Attribution par défaut, met tous les rangs à OMEGA
        for (Lycanthrope l : members) {
            l.joinPack(this, DominationRank.OMEGA);
        }

        // Ecraser le rang précédent pour forcer le couple alpha
        if (alphaMale != null) {
            alphaMale.joinPack(this, DominationRank.ALPHA);
        }

        if (alphaFemale != null) {
            alphaFemale.joinPack(this, DominationRank.ALPHA);
        }
    }


    // Décroissance naturelle des rang
    public void naturalRankDecrease(int threshold) {
        for (Lycanthrope l : members) {
            l.decreaseRank(threshold);
        }
    }


    // Affichage de la meute
    public void displayPack(){
        System.out.println("Pack: " + name);
        howl.display();
        System.out.println("Male alpha: " + alphaMale.getName());
        System.out.println("Female alpha: " + alphaFemale.getName());
    }

    // Affichage des membres de la meute
    public void displayPackMembers() {
        System.out.println("Meute : " + name);
        System.out.println("Membres :");

        for (Lycanthrope l : members) {
            String rang;

            if (l.getDominationRank() == null) {
                rang = "Solitaire";
            } else {
                rang = l.getDominationRank().toString();
            }

            System.out.println(l.getName() + " | " + rang);
        }

        System.out.println();
    }
}
