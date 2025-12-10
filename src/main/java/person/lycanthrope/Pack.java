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
        boolean alphaleft = false;
        if (l==alphaMale) {
            alphaleft = true;
            alphaMale = null;
        }

        if (l==alphaFemale) {
            alphaleft = true;
            alphaFemale = null;
        }

        if (alphaleft) {
            if (alphaMale == null) {
                quickSortByStrength(members, 0, members.size()-1);
                for (Lycanthrope member : members) {
                    if (member.getGender() == 'M') {
                        this.alphaMale = members.get(0);
                        this.alphaMale.joinPack(this, DominationRank.ALPHA);
                        System.out.println(this.alphaMale.getName() + " devient le nouveau mâle Alpha !");
                        break;
                    }
                }
            }
            if (alphaFemale == null) {
                quickSortByLevel(members, 0, members.size()-1);
                for (Lycanthrope member : members) {
                    if (member.getGender() == 'F') {
                        this.alphaFemale = members.get(0);
                        this.alphaFemale.joinPack(this, DominationRank.ALPHA);
                        System.out.println(this.alphaFemale.getName() + " devient le nouveau mâle Alpha !");
                        break;
                    }
                }
            }
        }
    }

    // Algorithme de quickSort pour supprimer un membre de la meute
    private void quickSortByStrength(List<Lycanthrope> list, int low, int high) {
        if (low < high) {
            int pi = partitionByStrength(list, low, high);
            quickSortByStrength(list, low, pi - 1);
            quickSortByStrength(list, pi + 1, high);
        }
    }

    private int partitionByStrength(List<Lycanthrope> list, int low, int high) {
        int pivot = list.get(high).getStrength();
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (list.get(j).getStrength() > pivot) {
                i++;
                Lycanthrope temp = list.get(i);
                list.set(i, list.get(j));
                list.set(j, temp);
            }
        }
        Lycanthrope temp = list.get(i + 1);
        list.set(i + 1, list.get(high));
        list.set(high, temp);
        return i + 1;
    }

    private void quickSortByLevel(List<Lycanthrope> list, int low, int high) {
        if (low < high) {
            int pi = partitionByLevel(list, low, high);
            quickSortByLevel(list, low, pi - 1);
            quickSortByLevel(list, pi + 1, high);
        }
    }

    private int partitionByLevel(List<Lycanthrope> list, int low, int high) {
        double pivot = list.get(high).getLevel();
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (list.get(j).getLevel() > pivot) { // DESCENDANT
                i++;
                Lycanthrope temp = list.get(i);
                list.set(i, list.get(j));
                list.set(j, temp);
            }
        }

        Lycanthrope temp = list.get(i + 1);
        list.set(i + 1, list.get(high));
        list.set(high, temp);
        return i + 1;
    }


    // Getteurs
    public Howl getHowl() {
        return howl;
    }

    public List<Lycanthrope> getMembers() {
        return members;
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

        if (alphaMale == null) {
            for (Lycanthrope member : members) {
                if (member.getGender() == 'M' && member.getDominationRank() == DominationRank.ALPHA) {
                    alphaMale = member;
                    break;
                }
            }
        }

        // Si alphaFemale est null mais que
        if (alphaFemale == null) {
            for (Lycanthrope member : members) {
                if (member.getGender() == 'F' && member.getDominationRank() == DominationRank.ALPHA) {
                    alphaFemale = member;
                    break;
                }
            }
        }


        // Ecraser le rang précédent pour forcer le couple alpha
        if (alphaMale != null) {
            alphaMale.joinPack(this, DominationRank.ALPHA);
        }

        if (alphaFemale != null) {
            alphaFemale.joinPack(this, DominationRank.ALPHA);
        }
    }


    // Méthode de destitution du male alpha et du calcul de la femelle
    public void handleAlphaDomination(Lycanthrope winner, Lycanthrope loser) {
        Lycanthrope oldAlphaFemale = alphaFemale;

        // Mise à jour du nouveau mâle alpha suite à la destitution
        if (loser == alphaMale) {
            alphaMale = winner;
            alphaMale.joinPack(this, DominationRank.ALPHA);
            System.out.println(alphaMale.getName() + " devient le nouveau mâle Alpha !");
        }

        // On récupère le rang de male alpha déchu
        DominationRank fallenMaleRank = loser.getDominationRank();

        // Calcul de la femelle alpha à partir du niveau
        quickSortByLevel(members, 0, members.size() - 1);

        for (Lycanthrope f : members) {
            if (f.getGender() == 'F' && f.getAgeCategory() == LycanthropeAge.ADULT) {
                alphaFemale = f;
                alphaFemale.joinPack(this, DominationRank.ALPHA);
                System.out.println(alphaFemale.getName() + " devient la nouvelle femelle Alpha !");
                break;
            }
        }

        if (oldAlphaFemale != null && oldAlphaFemale != alphaFemale) {
            oldAlphaFemale.joinPack(this, fallenMaleRank);
            System.out.println(oldAlphaFemale.getName() + " perd son statut Alpha et devient " + fallenMaleRank);
        }
    }


    // Décroissance naturelle des rang
    public void naturalRankDecrease(int threshold) {
        for (Lycanthrope l : members) {
            l.decreaseRank(threshold);
        }
    }

    // Vérification qu'un lycanthrope n'est pas le seul de son rang
    public boolean hasAnotherWithSameRank(Lycanthrope me) {
        for (Lycanthrope l : members) {
            if (l != me
                    && l.getGender() == me.getGender()
                    && l.getDominationRank() == me.getDominationRank()) {
                return true;
            }
        }
        return false;
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
