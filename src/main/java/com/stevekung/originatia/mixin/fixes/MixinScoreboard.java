package com.stevekung.originatia.mixin.fixes;

import java.util.Map;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.stevekung.originatia.utils.Utils;

import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.scoreboard.Scoreboard;

@Mixin(Scoreboard.class)
public class MixinScoreboard
{
    private final Scoreboard that = (Scoreboard) (Object) this;

    @Shadow
    @Final
    private Map<String, ScorePlayerTeam> teamMemberships;

    @Inject(method = "createTeam(Ljava/lang/String;)Lnet/minecraft/scoreboard/ScorePlayerTeam;", cancellable = true, at = @At(value = "INVOKE", target = "net/minecraft/scoreboard/Scoreboard.getTeam(Ljava/lang/String;)Lnet/minecraft/scoreboard/ScorePlayerTeam;", shift = Shift.AFTER))
    private void createTeam(String name, CallbackInfoReturnable<ScorePlayerTeam> info)
    {
        ScorePlayerTeam scoreplayerteam = this.that.getTeam(name);

        if (Utils.INSTANCE.isOriginRealms() && scoreplayerteam != null) // prevent error
        {
            info.setReturnValue(scoreplayerteam);
        }
    }

    @Inject(method = "removePlayerFromTeam(Ljava/lang/String;Lnet/minecraft/scoreboard/ScorePlayerTeam;)V", cancellable = true, at = @At("HEAD"))
    private void removePlayerFromTeam(String username, ScorePlayerTeam playerTeam, CallbackInfo info)
    {
        if (Utils.INSTANCE.isOriginRealms())
        {
            if (this.that.getPlayersTeam(username) != playerTeam)
            {
                info.cancel();
            }
            else
            {
                this.teamMemberships.remove(username);
                playerTeam.getMembershipCollection().remove(username);
            }
            info.cancel();
        }
    }
}