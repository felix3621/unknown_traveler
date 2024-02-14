package io.github.felix3621.unknown_traveler.block.entity.custom;

import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;

import java.util.HashMap;
import java.util.Map;

public class Animation {

    private record animationElement(Boolean play, RawAnimation animation) {}

    private final Map<String, animationElement> animationList;

    public Animation() {
        this.animationList = new HashMap<>();
    }

    public void registerAnimation(String name, RawAnimation animation) {
        this.animationList.put(name, new animationElement(false, animation));
    }

    private static void playAnimation(AnimationState state, RawAnimation animation) {
        state.getController().forceAnimationReset();
        state.getController().setAnimation(animation);
    }

    public void play(String name) {
        animationElement anim = this.animationList.get(name);
        if (anim != null) {
            this.animationList.put(name, new animationElement(true, anim.animation()));
        }
    }


    public <E extends TardisExteriorBlockEntity> void TEBEanimationTick(AnimationState<E> state) {
        for (Map.Entry<String, animationElement> entry : this.animationList.entrySet()) {
            if (entry.getValue().play) {
                playAnimation(state, entry.getValue().animation());
                this.animationList.put(entry.getKey(), new animationElement(false, entry.getValue().animation()));
            }
        }
    }

    public <E extends TardisExteriorBlockEntityOpen> void TEBEOanimationTick(AnimationState<E> state) {
        for (Map.Entry<String, animationElement> entry : this.animationList.entrySet()) {
            if (entry.getValue().play) {
                playAnimation(state, entry.getValue().animation());
                this.animationList.put(entry.getKey(), new animationElement(false, entry.getValue().animation()));
            }
        }
    }
}
