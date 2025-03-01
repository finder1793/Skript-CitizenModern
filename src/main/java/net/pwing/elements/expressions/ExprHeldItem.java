package net.pwing.elements.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.event.Event;
import org.bukkit.entity.LivingEntity;

import javax.annotation.Nullable;

public class ExprHeldItem extends SimpleExpression<ItemStack> {
    private Expression<Integer> npcId;

    @Override
    protected ItemStack[] get(Event e) {
        NPC npc = getNPC(e);
        if (npc != null && npc.getEntity() instanceof LivingEntity) {
            LivingEntity entity = (LivingEntity) npc.getEntity();
            return new ItemStack[]{entity.getEquipment().getItem(EquipmentSlot.HAND)};
        }
        return null;
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends ItemStack> getReturnType() {
        return ItemStack.class;
    }

    @Override
    public String toString(@Nullable Event event, boolean b) {
        return "held item of citizen with id " + npcId.toString(event, b);
    }

    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
        npcId = (Expression<Integer>) expressions[0];
        return true;
    }

    @Override
    public void change(Event e, Object[] delta, Changer.ChangeMode mode) {
        NPC npc = getNPC(e);
        if (npc != null && npc.getEntity() instanceof LivingEntity) {
            LivingEntity entity = (LivingEntity) npc.getEntity();
            entity.getEquipment().setItem(EquipmentSlot.HAND, (ItemStack) delta[0]);
        }
    }

    @Override
    public Class<?>[] acceptChange(Changer.ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET) {
            return new Class[]{ItemStack.class};
        }
        return null;
    }

    private NPC getNPC(Event event) {
        Integer id = npcId.getSingle(event);
        if (id != null) {
            return CitizensAPI.getNPCRegistry().getById(id);
        }
        return null;
    }
}
