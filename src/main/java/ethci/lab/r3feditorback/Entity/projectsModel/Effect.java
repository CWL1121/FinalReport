package ethci.lab.r3feditorback.Entity.projectsModel;

public class Effect {
    public SSR ssr;
    public Bloom bloom;
    public Noise noise;
    public Effect() {
    }
    public Effect(SSR ssr, Bloom bloom, Noise noise) {
        this.ssr = ssr;
        this.bloom = bloom;
        this.noise = noise;
    }
}
