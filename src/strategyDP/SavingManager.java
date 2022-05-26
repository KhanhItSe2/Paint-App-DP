package strategyDP;

public class SavingManager implements SavingMethod {

    private SavingMethod saving;

    public SavingManager(SavingMethod saving) {
        this.saving = saving;
    }

    @Override
    public void save() {
        saving.save();

    }

}
