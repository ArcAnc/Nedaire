/*
 * Ancient
 * Created at: 01-06-2020
 * Copyright (c) 2020
 *
 * This code is licensed under "Ancient's License of Common Sense"
 * Details can be found in the license file in the root folder of this project
 */
package com.ancient.nedaire.content.items.weapon;

import com.ancient.nedaire.Nedaire;
import com.ancient.nedaire.content.materials.tool.NAbstractToolMaterial;

import net.minecraft.item.CrossbowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * FIXME: перевести на нормальний код. Убрать все корейские названия
 */

public class NCrossbowBase extends CrossbowItem
{
	protected final NAbstractToolMaterial material;

    protected boolean field_220034_c;
    protected boolean field_220035_d;
	
	public NCrossbowBase(NAbstractToolMaterial toolMat) 
	{
		super(new Item.Properties().group(Nedaire.instance.TAB).maxDamage(toolMat.getMaxUses()));
		
		this.material = toolMat;
		field_220034_c = false;
		field_220035_d = false;
	}

	@Override
	public boolean isCrossbow(ItemStack stack) 
	{
		return stack.getItem() instanceof CrossbowItem;
	}
	
	@Override
	protected String getDefaultTranslationKey() 
	{
		return getRegistryName().toString().replace(':', '.').replace('/', '.');
	}

	
/*	@Override
	public ActionResult<ItemStack> onItemRightClick(World p_77659_1_, PlayerEntity p_77659_2_, Hand p_77659_3_) 
	{
        final ItemStack lvt_4_1_ = p_77659_2_.getHeldItem(p_77659_3_);
        if (isCharged(lvt_4_1_)) 
        {
            fireProjectilesCustom(p_77659_1_, (LivingEntity)p_77659_2_, p_77659_3_, lvt_4_1_, func_220013_l(lvt_4_1_), 1.0f);
            setCharged(lvt_4_1_, false);
            return new ActionResult<>(ActionResultType.SUCCESS, lvt_4_1_);
        }
        if (!p_77659_2_.findAmmo(lvt_4_1_).isEmpty()) 
        {
            if (!isCharged(lvt_4_1_)) 
            {
                field_220034_c = false;
                field_220035_d = false;
                p_77659_2_.setActiveHand(p_77659_3_);
            }
            return new ActionResult<>(ActionResultType.SUCCESS, lvt_4_1_);
        }
        return new ActionResult<>(ActionResultType.FAIL, lvt_4_1_);
	}
	
	@Override
	public void onPlayerStoppedUsing(ItemStack p_77615_1_, World p_77615_2_, LivingEntity p_77615_3_, int p_77615_4_) 
	{
        final int lvt_5_1_ = this.getUseDuration(p_77615_1_) - p_77615_4_;
        final float lvt_6_1_ = getCharge(lvt_5_1_, p_77615_1_);
        if (lvt_6_1_ >= 1.0f && !isCharged(p_77615_1_) && hasAmmo(p_77615_3_, p_77615_1_)) 
        {
            setCharged(p_77615_1_, true);
            final SoundCategory lvt_7_1_ = (p_77615_3_ instanceof PlayerEntity) ? SoundCategory.PLAYERS : SoundCategory.HOSTILE;
            p_77615_2_.playSound((PlayerEntity)null, p_77615_3_.getPosX(), p_77615_3_.getPosY(), p_77615_3_.getPosZ(), SoundEvents.ITEM_CROSSBOW_LOADING_END, lvt_7_1_, 1.0f, 1.0f / (CrossbowItem.random.nextFloat() * 0.5f + 1.0f) + 0.2f);
        }	
	}
	
	@OnlyIn(Dist.CLIENT)
	@Override
	public void addInformation(ItemStack p_77624_1_, World p_77624_2_, List<ITextComponent> p_77624_3_,	ITooltipFlag p_77624_4_) 
	{
        final List<ItemStack> lvt_5_1_ = getChargedProjectiles(p_77624_1_);
        if (!isCharged(p_77624_1_) || lvt_5_1_.isEmpty()) 
        {
            return;
        }
        final ItemStack lvt_6_1_ = lvt_5_1_.get(0);
        p_77624_3_.add(new TranslationTextComponent("item.minecraft.crossbow.projectile", new Object[0]).appendText(" ").appendSibling(lvt_6_1_.getTextComponent()));
        if (p_77624_4_.isAdvanced() && lvt_6_1_.getItem() == Items.FIREWORK_ROCKET) 
        {
            List<ITextComponent> lvt_7_1_ = Lists.<ITextComponent>newArrayList();
            Items.FIREWORK_ROCKET.addInformation(lvt_6_1_, p_77624_2_, lvt_7_1_, p_77624_4_);
            if (!lvt_7_1_.isEmpty()) {
                for (int lvt_8_1_ = 0; lvt_8_1_ < lvt_7_1_.size(); ++lvt_8_1_) 
                {
                    lvt_7_1_.set(lvt_8_1_, new StringTextComponent("  ").appendSibling((ITextComponent)lvt_7_1_.get(lvt_8_1_)).applyTextStyle(TextFormatting.GRAY));
                }
                p_77624_3_.addAll(lvt_7_1_);
            }
        }	
	}
	
    @Override
	public void onUse(final World p_219972_1_, final LivingEntity p_219972_2_, final ItemStack p_219972_3_, final int p_219972_4_) 
    {
        if (!p_219972_1_.isRemote()) 
        {
            final int lvt_5_1_ = EnchantmentHelper.getEnchantmentLevel(Enchantments.QUICK_CHARGE, p_219972_3_);
            final SoundEvent lvt_6_1_ = func_220025_a(lvt_5_1_);
            final SoundEvent lvt_7_1_ = (lvt_5_1_ == 0) ? SoundEvents.ITEM_CROSSBOW_LOADING_MIDDLE : null;
            final float lvt_8_1_ = (p_219972_3_.getUseDuration() - p_219972_4_) / (float)getChargeTime(p_219972_3_);
            if (lvt_8_1_ < 0.2f) 
            {
                field_220034_c = false;
                field_220035_d = false;
            }
            if (lvt_8_1_ >= 0.2f && !field_220034_c) 
            {
                field_220034_c = true;
                p_219972_1_.playSound((PlayerEntity)null, p_219972_2_.getPosX(), p_219972_2_.getPosY(), p_219972_2_.getPosZ(), lvt_6_1_, SoundCategory.PLAYERS, 0.5f, 1.0f);
            }
            if (lvt_8_1_ >= 0.5f && lvt_7_1_ != null && !field_220035_d) 
            {
                field_220035_d = true;
                p_219972_1_.playSound((PlayerEntity)null, p_219972_2_.getPosX(), p_219972_2_.getPosY(), p_219972_2_.getPosZ(), lvt_7_1_, SoundCategory.PLAYERS, 0.5f, 1.0f);
            }
        }
    }
    
    protected void clearProjectiles(final ItemStack p_220027_0_) 
    {
        final CompoundNBT lvt_1_1_ = p_220027_0_.getTag();
        if (lvt_1_1_ != null) 
        {
            final ListNBT lvt_2_1_ = lvt_1_1_.getList("ChargedProjectiles", 9);
            lvt_2_1_.clear();
            lvt_1_1_.put("ChargedProjectiles", (INBT)lvt_2_1_);
        }
    }
    
    protected void func_220015_a(final World p_220015_0_, final LivingEntity p_220015_1_, final ItemStack p_220015_2_) 
    {
        if (p_220015_1_ instanceof ServerPlayerEntity) 
        {
            final ServerPlayerEntity lvt_3_1_ = (ServerPlayerEntity)p_220015_1_;
            if (!p_220015_0_.isRemote) 
            {
                CriteriaTriggers.SHOT_CROSSBOW.func_215111_a(lvt_3_1_, p_220015_2_);
            }
            lvt_3_1_.addStat(Stats.ITEM_USED.get(p_220015_2_.getItem()));
        }
        clearProjectiles(p_220015_2_);
    }
    
    public void fireProjectilesCustom(final World p_220014_0_, final LivingEntity p_220014_1_, final Hand p_220014_2_, final ItemStack p_220014_3_, final float p_220014_4_, final float p_220014_5_) 
    {
        final List<ItemStack> lvt_6_1_ = getChargedProjectiles(p_220014_3_);
        final float[] lvt_7_1_ = func_220028_a(p_220014_1_.getRNG());
        for (int lvt_8_1_ = 0; lvt_8_1_ < lvt_6_1_.size(); ++lvt_8_1_) 
        {
            final ItemStack lvt_9_1_ = lvt_6_1_.get(lvt_8_1_);
            final boolean lvt_10_1_ = p_220014_1_ instanceof PlayerEntity && ((PlayerEntity)p_220014_1_).abilities.isCreativeMode;
            if (!lvt_9_1_.isEmpty()) 
            {
                if (lvt_8_1_ == 0) 
                {
                    func_220016_a(p_220014_0_, p_220014_1_, p_220014_2_, p_220014_3_, lvt_9_1_, lvt_7_1_[lvt_8_1_], lvt_10_1_, p_220014_4_, p_220014_5_, 0.0f);
                }
                else if (lvt_8_1_ == 1) 
                {
                    func_220016_a(p_220014_0_, p_220014_1_, p_220014_2_, p_220014_3_, lvt_9_1_, lvt_7_1_[lvt_8_1_], lvt_10_1_, p_220014_4_, p_220014_5_, -10.0f);
                }
                else if (lvt_8_1_ == 2) 
                {
                    func_220016_a(p_220014_0_, p_220014_1_, p_220014_2_, p_220014_3_, lvt_9_1_, lvt_7_1_[lvt_8_1_], lvt_10_1_, p_220014_4_, p_220014_5_, 10.0f);
                }
            }
        }
        func_220015_a(p_220014_0_, p_220014_1_, p_220014_3_);
    }
    
    protected float[] func_220028_a(final Random p_220028_0_) 
    {
        final boolean lvt_1_1_ = p_220028_0_.nextBoolean();
        return new float[] { 1.0f, func_220032_a(lvt_1_1_), func_220032_a(!lvt_1_1_) };
    }
    
    protected float func_220032_a(final boolean p_220032_0_) 
    {
        final float lvt_1_1_ = p_220032_0_ ? 0.63f : 0.43f;
        return 1.0f / (CrossbowItem.random.nextFloat() * 0.5f + 1.8f) + lvt_1_1_;
    }
    
    protected void func_220016_a(final World p_220016_0_, final LivingEntity p_220016_1_, final Hand p_220016_2_, final ItemStack p_220016_3_, final ItemStack p_220016_4_, final float p_220016_5_, final boolean p_220016_6_, final float p_220016_7_, final float p_220016_8_, final float p_220016_9_) 
    {
        if (p_220016_0_.isRemote) 
        {
            return;
        }
        final boolean lvt_10_1_ = p_220016_4_.getItem() == Items.FIREWORK_ROCKET;
        IProjectile lvt_11_2_ = null;
        if (lvt_10_1_) 
        {
            new FireworkRocketEntity(p_220016_0_, p_220016_4_, p_220016_1_.getPosX(), p_220016_1_.getPosY() + p_220016_1_.getEyeHeight() - 0.15000000596046448, p_220016_1_.getPosZ(), true);
        }
        else 
        {
            lvt_11_2_ = (IProjectile)createArrow(p_220016_0_, p_220016_1_, p_220016_3_, p_220016_4_);
            if (p_220016_6_ || p_220016_9_ != 0.0f) 
            {
                ((AbstractArrowEntity)lvt_11_2_).pickupStatus = AbstractArrowEntity.PickupStatus.CREATIVE_ONLY;
            }
        }
        if (p_220016_1_ instanceof ICrossbowUser) 
        {
            final ICrossbowUser lvt_12_1_ = (ICrossbowUser)p_220016_1_;
            lvt_12_1_.shoot(lvt_12_1_.getAttackTarget(), p_220016_3_, lvt_11_2_, p_220016_9_);
        }
        else 
        {
            final Vec3d lvt_12_2_ = p_220016_1_.getUpVector(1.0f);
            final Quaternion lvt_13_1_ = new Quaternion(new Vector3f(lvt_12_2_), p_220016_9_, true);
            final Vec3d lvt_14_1_ = p_220016_1_.getLook(1.0f);
            final Vector3f lvt_15_1_ = new Vector3f(lvt_14_1_);
            lvt_15_1_.transform(lvt_13_1_);
            lvt_11_2_.shoot((double)lvt_15_1_.getX(), (double)lvt_15_1_.getY(), (double)lvt_15_1_.getZ(), p_220016_7_, p_220016_8_);
        }
        p_220016_3_.damageItem(lvt_10_1_ ? 3 : 1, p_220016_1_, p_220017_1_ -> p_220017_1_.sendBreakAnimation(p_220016_2_));
        p_220016_0_.addEntity((Entity)lvt_11_2_);
        p_220016_0_.playSound((PlayerEntity)null, p_220016_1_.getPosX(), p_220016_1_.getPosY(), p_220016_1_.getPosZ(), SoundEvents.ITEM_CROSSBOW_SHOOT, SoundCategory.PLAYERS, 1.0f, p_220016_5_);
    }
    
    protected AbstractArrowEntity createArrow(final World p_220024_0_, final LivingEntity p_220024_1_, final ItemStack p_220024_2_, final ItemStack p_220024_3_) 
    {
        final ArrowItem lvt_4_1_ = (ArrowItem)((p_220024_3_.getItem() instanceof ArrowItem) ? p_220024_3_.getItem() : Items.ARROW);
        final AbstractArrowEntity lvt_5_1_ = lvt_4_1_.createArrow(p_220024_0_, p_220024_3_, p_220024_1_);
        if (p_220024_1_ instanceof PlayerEntity) 
        {
            lvt_5_1_.setIsCritical(true);
        }
        lvt_5_1_.setHitSound(SoundEvents.ITEM_CROSSBOW_HIT);
        lvt_5_1_.setShotFromCrossbow(true);
        final int lvt_6_1_ = EnchantmentHelper.getEnchantmentLevel(Enchantments.PIERCING, p_220024_2_);
        if (lvt_6_1_ > 0) 
        {
            lvt_5_1_.setPierceLevel((byte)lvt_6_1_);
        }
        return lvt_5_1_;
    }
	
    protected SoundEvent func_220025_a(final int p_220025_1_) 
    {
        switch (p_220025_1_) 
        {
            case 1: 
            {
                return SoundEvents.ITEM_CROSSBOW_QUICK_CHARGE_1;
            }
            case 2: 
            {
                return SoundEvents.ITEM_CROSSBOW_QUICK_CHARGE_2;
            }
            case 3: 
            {
                return SoundEvents.ITEM_CROSSBOW_QUICK_CHARGE_3;
            }
            default: 
            {
                return SoundEvents.ITEM_CROSSBOW_LOADING_START;
            }
        }
    }
    
    protected boolean hasAmmo(final LivingEntity p_220021_0_, final ItemStack p_220021_1_) 
    {
        final int lvt_2_1_ = EnchantmentHelper.getEnchantmentLevel(Enchantments.MULTISHOT, p_220021_1_);
        final int lvt_3_1_ = (lvt_2_1_ == 0) ? 1 : 3;
        final boolean lvt_4_1_ = p_220021_0_ instanceof PlayerEntity && ((PlayerEntity)p_220021_0_).abilities.isCreativeMode;
        ItemStack lvt_5_1_ = p_220021_0_.findAmmo(p_220021_1_);
        ItemStack lvt_6_1_ = lvt_5_1_.copy();
        for (int lvt_7_1_ = 0; lvt_7_1_ < lvt_3_1_; ++lvt_7_1_) 
        {
            if (lvt_7_1_ > 0) 
            {
                lvt_5_1_ = lvt_6_1_.copy();
            }
            if (lvt_5_1_.isEmpty() && lvt_4_1_) 
            {
                lvt_5_1_ = new ItemStack((IItemProvider)Items.ARROW);
                lvt_6_1_ = lvt_5_1_.copy();
            }
            if (!func_220023_a(p_220021_0_, p_220021_1_, lvt_5_1_, lvt_7_1_ > 0, lvt_4_1_)) 
            {
                return false;
            }
        }
        return true;
    }
    
    protected boolean func_220023_a(final LivingEntity p_220023_0_, final ItemStack p_220023_1_, final ItemStack p_220023_2_, final boolean p_220023_3_, final boolean p_220023_4_) 
    {
        if (p_220023_2_.isEmpty()) 
        {
            return false;
        }
        final boolean lvt_5_1_ = p_220023_4_ && p_220023_2_.getItem() instanceof ArrowItem;
        ItemStack lvt_6_2_ = null;
        if (!lvt_5_1_ && !p_220023_4_ && !p_220023_3_) 
        {
            p_220023_2_.split(1);
            if (p_220023_2_.isEmpty() && p_220023_0_ instanceof PlayerEntity) 
            {
                ((PlayerEntity)p_220023_0_).inventory.deleteStack(p_220023_2_);
            }
        }
        else 
        {
            lvt_6_2_ = p_220023_2_.copy();
        }
        addChargedProjectile(p_220023_1_, lvt_6_2_);
        return true;
    }
    
    protected void addChargedProjectile(final ItemStack p_220029_0_, final ItemStack p_220029_1_) 
    {
        final CompoundNBT lvt_2_1_ = p_220029_0_.getOrCreateTag();
        ListNBT lvt_3_2_ = null;
        if (lvt_2_1_.contains("ChargedProjectiles", 9)) 
        {
        	lvt_3_2_ =  lvt_2_1_.getList("ChargedProjectiles", 10);
        }
        else 
        {
            lvt_3_2_ = new ListNBT();
        }
        final CompoundNBT lvt_4_1_ = new CompoundNBT();
        p_220029_1_.write(lvt_4_1_);
        lvt_3_2_.add(lvt_4_1_);
        lvt_2_1_.put("ChargedProjectiles", (INBT)lvt_3_2_);
    }
    
    protected List<ItemStack> getChargedProjectiles(final ItemStack p_220018_0_) 
    {
        final List<ItemStack> lvt_1_1_ = Lists.<ItemStack>newArrayList();
        final CompoundNBT lvt_2_1_ = p_220018_0_.getTag();
        if (lvt_2_1_ != null && lvt_2_1_.contains("ChargedProjectiles", 9)) 
        {
            final ListNBT lvt_3_1_ = lvt_2_1_.getList("ChargedProjectiles", 10);
            if (lvt_3_1_ != null) 
            {
                for (int lvt_4_1_ = 0; lvt_4_1_ < lvt_3_1_.size(); ++lvt_4_1_) 
                {
                    final CompoundNBT lvt_5_1_ = lvt_3_1_.getCompound(lvt_4_1_);
                    lvt_1_1_.add(ItemStack.read(lvt_5_1_));
                }
            }
        }
        return lvt_1_1_;
    }
    
    protected boolean hasChargedProjectile(final ItemStack p_220019_0_, final Item p_220019_1_) 
    {
        return getChargedProjectiles(p_220019_0_).stream().anyMatch(p_220010_1_ -> p_220010_1_.getItem() == p_220019_1_);
    }
    
    protected float getCharge(final int p_220031_0_, final ItemStack p_220031_1_) 
    {
    	float lvt_2_1_ = p_220031_0_ / (float)getChargeTime(p_220031_1_);
    	if (lvt_2_1_ > 1.0f) 
        {
            lvt_2_1_ = 1.0f;
        }
        return lvt_2_1_;
    }
    
    protected float func_220013_l(final ItemStack p_220013_0_) 
    {
        if (p_220013_0_.getItem() instanceof CrossbowItem && hasChargedProjectile(p_220013_0_, Items.FIREWORK_ROCKET)) 
        {
            return 1.6f;
        }
        return 3.15f;
    }
    
*/    
}
