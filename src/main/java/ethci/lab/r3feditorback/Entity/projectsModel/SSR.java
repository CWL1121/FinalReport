package ethci.lab.r3feditorback.Entity.projectsModel;
import java.util.UUID;
public class SSR {
    public String _id;
    public boolean temporalResolve;
    public boolean STRETCH_MISSED_RAYS;
    public boolean USE_MRT;
    public boolean USE_NORMALMAP;
    public boolean USE_ROUGHNESSMAP;
    public boolean ENABLE_JITTERING;
    public boolean ENABLE_BLUR;
    public float temporalResolveMix;
    public float temporalResolveCorrectionMix;
    public float maxSamples;
    public float resolutionScale;
    public float blurMix;
    public float blurExponent;
    public float blurKernelSize;
    public float rayStep;
    public float intensity;
    public float maxRoughness;
    public float jitter;
    public float jitterSpread;
    public float jitterRough;
    public float roughnessFadeOut;
    public float rayFadeOut;
    public float MAX_STEPS;
    public float NUM_BINARY_SEARCH_STEPS;
    public float maxDepthDifference;
    public float maxDepth;
    public float thickness;
    public float ior;

    public SSR() {

    }

    public SSR( boolean temporalResolve, boolean STRETCH_MISSED_RAYS, boolean USE_MRT, boolean USE_NORMALMAP, boolean USE_ROUGHNESSMAP, boolean ENABLE_JITTERING, boolean ENABLE_BLUR, float temporalResolveMix, float temporalResolveCorrectionMix, float maxSamples, float resolutionScale, float blurMix, float blurExponent, float blurKernelSize, float rayStep, float intensity, float maxRoughness, float jitter, float jitterSpread, float jitterRough, float roughnessFadeOut, float rayFadeOut, float MAX_STEPS, float NUM_BINARY_SEARCH_STEPS, float maxDepthDifference, float maxDepth, float thickness, float ior) {
        this._id = UUID.randomUUID().toString().replace("-","");
        this.temporalResolve = temporalResolve;
        this.STRETCH_MISSED_RAYS = STRETCH_MISSED_RAYS;
        this.USE_MRT = USE_MRT;
        this.USE_NORMALMAP = USE_NORMALMAP;
        this.USE_ROUGHNESSMAP = USE_ROUGHNESSMAP;
        this.ENABLE_JITTERING = ENABLE_JITTERING;
        this.ENABLE_BLUR = ENABLE_BLUR;
        this.temporalResolveMix = temporalResolveMix;
        this.temporalResolveCorrectionMix = temporalResolveCorrectionMix;
        this.maxSamples = maxSamples;
        this.resolutionScale = resolutionScale;
        this.blurMix = blurMix;
        this.blurExponent = blurExponent;
        this.blurKernelSize = blurKernelSize;
        this.rayStep = rayStep;
        this.intensity = intensity;
        this.maxRoughness = maxRoughness;
        this.jitter = jitter;
        this.jitterSpread = jitterSpread;
        this.jitterRough = jitterRough;
        this.roughnessFadeOut = roughnessFadeOut;
        this.rayFadeOut = rayFadeOut;
        this.MAX_STEPS = MAX_STEPS;
        this.NUM_BINARY_SEARCH_STEPS = NUM_BINARY_SEARCH_STEPS;
        this.maxDepthDifference = maxDepthDifference;
        this.maxDepth = maxDepth;
        this.thickness = thickness;
        this.ior = ior;
    }
}
