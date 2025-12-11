package place.types;

import clanLeader.ClanLeader;
import person.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe représentant un champ de bataille.
 *
 * <p>Un champ de bataille peut contenir tous les types de personnages.
 *
 * <p>Hérite de Place et bénéficie donc des méthodes pour
 * gérer les habitants et leur alimentation/soin.
 */
public class BattleField extends place.Place {

    private List<ClanLeader> EngagedClanLeaders = new ArrayList<ClanLeader>();

    /**
     * Vérifie si une personne peut entrer dans ce lieu
     *
     * @param person La personne à tester
     * @return true si la personne peut entrer, false sinon
     */
    @Override
    public boolean canAddPerson(Person person) {
        EngagedClanLeaders.add(person.getOwner());
        return true;
    }

    public List<ClanLeader> removeEngagedClanLeaders(ClanLeader clanLeader) {
        EngagedClanLeaders.remove(clanLeader);
        return EngagedClanLeaders;
    }

    @Override
    public String getType() {
        return "Champ de bataille";
    }

    public void viewBattleField() {
        if (EngagedClanLeaders.isEmpty()) {
            System.out.println("Le champ de bataille est vide.");
            return;
        }
        for(ClanLeader clanLeader : EngagedClanLeaders) {
            System.out.println("-------------");
            System.out.println(clanLeader.getName());
            for(Person person : this.getListOfPersons()){
                if(person.getOwner().equals(clanLeader)){
                    System.out.println(person.getName()+" {"+person.getHealth()+"} ");
                }

            }
        }
    }
}

