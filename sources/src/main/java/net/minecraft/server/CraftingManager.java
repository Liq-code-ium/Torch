package net.minecraft.server;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.koloboke.collect.map.hash.HashObjObjMaps;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.bukkit.craftbukkit.event.CraftEventFactory; // CraftBukkit

public class CraftingManager {

    private static final CraftingManager a = new CraftingManager();
    public List<IRecipe> recipes = Lists.newArrayList();
    // CraftBukkit start
    public IRecipe lastRecipe;
    public org.bukkit.inventory.InventoryView lastCraftView;
    // CraftBukkit end

    public static CraftingManager getInstance() {
        return CraftingManager.a;
    }

    public CraftingManager() {
        (new RecipesTools()).a(this);
        (new RecipesWeapons()).a(this);
        (new RecipeIngots()).a(this);
        (new RecipesFood()).a(this);
        (new RecipesCrafting()).a(this);
        (new RecipesArmor()).a(this);
        (new RecipesDyes()).a(this);
        this.recipes.add(new RecipeArmorDye());
        this.recipes.add(new RecipeBookClone());
        this.recipes.add(new RecipeMapClone());
        this.recipes.add(new RecipeMapExtend());
        this.recipes.add(new RecipeFireworks());
        this.recipes.add(new RecipeRepair());
        this.recipes.add(new RecipeTippedArrow());
        (new RecipesBanner()).a(this);
        (new RecipiesShield()).a(this);
        (new RecipeShulkerBox()).a(this);
        this.registerShapedRecipe(new ItemStack(Items.PAPER, 3), new Object[] { "###", Character.valueOf('#'), Items.REEDS});
        this.registerShapelessRecipe(new ItemStack(Items.BOOK, 1), new Object[] { Items.PAPER, Items.PAPER, Items.PAPER, Items.LEATHER});
        this.registerShapelessRecipe(new ItemStack(Items.WRITABLE_BOOK, 1), new Object[] { Items.BOOK, new ItemStack(Items.DYE, 1, EnumColor.BLACK.getInvColorIndex()), Items.FEATHER});
        this.registerShapedRecipe(new ItemStack(Blocks.FENCE, 3), new Object[] { "W#W", "W#W", Character.valueOf('#'), Items.STICK, Character.valueOf('W'), new ItemStack(Blocks.PLANKS, 1, BlockWood.EnumLogVariant.OAK.a())});
        this.registerShapedRecipe(new ItemStack(Blocks.BIRCH_FENCE, 3), new Object[] { "W#W", "W#W", Character.valueOf('#'), Items.STICK, Character.valueOf('W'), new ItemStack(Blocks.PLANKS, 1, BlockWood.EnumLogVariant.BIRCH.a())});
        this.registerShapedRecipe(new ItemStack(Blocks.SPRUCE_FENCE, 3), new Object[] { "W#W", "W#W", Character.valueOf('#'), Items.STICK, Character.valueOf('W'), new ItemStack(Blocks.PLANKS, 1, BlockWood.EnumLogVariant.SPRUCE.a())});
        this.registerShapedRecipe(new ItemStack(Blocks.JUNGLE_FENCE, 3), new Object[] { "W#W", "W#W", Character.valueOf('#'), Items.STICK, Character.valueOf('W'), new ItemStack(Blocks.PLANKS, 1, BlockWood.EnumLogVariant.JUNGLE.a())});
        this.registerShapedRecipe(new ItemStack(Blocks.ACACIA_FENCE, 3), new Object[] { "W#W", "W#W", Character.valueOf('#'), Items.STICK, Character.valueOf('W'), new ItemStack(Blocks.PLANKS, 1, 4 + BlockWood.EnumLogVariant.ACACIA.a() - 4)});
        this.registerShapedRecipe(new ItemStack(Blocks.DARK_OAK_FENCE, 3), new Object[] { "W#W", "W#W", Character.valueOf('#'), Items.STICK, Character.valueOf('W'), new ItemStack(Blocks.PLANKS, 1, 4 + BlockWood.EnumLogVariant.DARK_OAK.a() - 4)});
        this.registerShapedRecipe(new ItemStack(Blocks.COBBLESTONE_WALL, 6, BlockCobbleWall.EnumCobbleVariant.NORMAL.a()), new Object[] { "###", "###", Character.valueOf('#'), Blocks.COBBLESTONE});
        this.registerShapedRecipe(new ItemStack(Blocks.COBBLESTONE_WALL, 6, BlockCobbleWall.EnumCobbleVariant.MOSSY.a()), new Object[] { "###", "###", Character.valueOf('#'), Blocks.MOSSY_COBBLESTONE});
        this.registerShapedRecipe(new ItemStack(Blocks.NETHER_BRICK_FENCE, 6), new Object[] { "###", "###", Character.valueOf('#'), Blocks.NETHER_BRICK});
        this.registerShapedRecipe(new ItemStack(Blocks.FENCE_GATE, 1), new Object[] { "#W#", "#W#", Character.valueOf('#'), Items.STICK, Character.valueOf('W'), new ItemStack(Blocks.PLANKS, 1, BlockWood.EnumLogVariant.OAK.a())});
        this.registerShapedRecipe(new ItemStack(Blocks.BIRCH_FENCE_GATE, 1), new Object[] { "#W#", "#W#", Character.valueOf('#'), Items.STICK, Character.valueOf('W'), new ItemStack(Blocks.PLANKS, 1, BlockWood.EnumLogVariant.BIRCH.a())});
        this.registerShapedRecipe(new ItemStack(Blocks.SPRUCE_FENCE_GATE, 1), new Object[] { "#W#", "#W#", Character.valueOf('#'), Items.STICK, Character.valueOf('W'), new ItemStack(Blocks.PLANKS, 1, BlockWood.EnumLogVariant.SPRUCE.a())});
        this.registerShapedRecipe(new ItemStack(Blocks.JUNGLE_FENCE_GATE, 1), new Object[] { "#W#", "#W#", Character.valueOf('#'), Items.STICK, Character.valueOf('W'), new ItemStack(Blocks.PLANKS, 1, BlockWood.EnumLogVariant.JUNGLE.a())});
        this.registerShapedRecipe(new ItemStack(Blocks.ACACIA_FENCE_GATE, 1), new Object[] { "#W#", "#W#", Character.valueOf('#'), Items.STICK, Character.valueOf('W'), new ItemStack(Blocks.PLANKS, 1, 4 + BlockWood.EnumLogVariant.ACACIA.a() - 4)});
        this.registerShapedRecipe(new ItemStack(Blocks.DARK_OAK_FENCE_GATE, 1), new Object[] { "#W#", "#W#", Character.valueOf('#'), Items.STICK, Character.valueOf('W'), new ItemStack(Blocks.PLANKS, 1, 4 + BlockWood.EnumLogVariant.DARK_OAK.a() - 4)});
        this.registerShapedRecipe(new ItemStack(Blocks.JUKEBOX, 1), new Object[] { "###", "#X#", "###", Character.valueOf('#'), Blocks.PLANKS, Character.valueOf('X'), Items.DIAMOND});
        this.registerShapedRecipe(new ItemStack(Items.LEAD, 2), new Object[] { "~~ ", "~O ", "  ~", Character.valueOf('~'), Items.STRING, Character.valueOf('O'), Items.SLIME});
        this.registerShapedRecipe(new ItemStack(Blocks.NOTEBLOCK, 1), new Object[] { "###", "#X#", "###", Character.valueOf('#'), Blocks.PLANKS, Character.valueOf('X'), Items.REDSTONE});
        this.registerShapedRecipe(new ItemStack(Blocks.BOOKSHELF, 1), new Object[] { "###", "XXX", "###", Character.valueOf('#'), Blocks.PLANKS, Character.valueOf('X'), Items.BOOK});
        this.registerShapedRecipe(new ItemStack(Blocks.SNOW, 1), new Object[] { "##", "##", Character.valueOf('#'), Items.SNOWBALL});
        this.registerShapedRecipe(new ItemStack(Blocks.SNOW_LAYER, 6), new Object[] { "###", Character.valueOf('#'), Blocks.SNOW});
        this.registerShapedRecipe(new ItemStack(Blocks.CLAY, 1), new Object[] { "##", "##", Character.valueOf('#'), Items.CLAY_BALL});
        this.registerShapedRecipe(new ItemStack(Blocks.BRICK_BLOCK, 1), new Object[] { "##", "##", Character.valueOf('#'), Items.BRICK});
        this.registerShapedRecipe(new ItemStack(Blocks.GLOWSTONE, 1), new Object[] { "##", "##", Character.valueOf('#'), Items.GLOWSTONE_DUST});
        this.registerShapedRecipe(new ItemStack(Blocks.QUARTZ_BLOCK, 1), new Object[] { "##", "##", Character.valueOf('#'), Items.QUARTZ});
        this.registerShapedRecipe(new ItemStack(Blocks.WOOL, 1), new Object[] { "##", "##", Character.valueOf('#'), Items.STRING});
        this.registerShapedRecipe(new ItemStack(Blocks.TNT, 1), new Object[] { "X#X", "#X#", "X#X", Character.valueOf('X'), Items.GUNPOWDER, Character.valueOf('#'), Blocks.SAND});
        this.registerShapedRecipe(new ItemStack(Blocks.STONE_SLAB, 6, BlockDoubleStepAbstract.EnumStoneSlabVariant.COBBLESTONE.a()), new Object[] { "###", Character.valueOf('#'), Blocks.COBBLESTONE});
        this.registerShapedRecipe(new ItemStack(Blocks.STONE_SLAB, 6, BlockDoubleStepAbstract.EnumStoneSlabVariant.STONE.a()), new Object[] { "###", Character.valueOf('#'), new ItemStack(Blocks.STONE, 1, BlockStone.EnumStoneVariant.STONE.a())});
        this.registerShapedRecipe(new ItemStack(Blocks.STONE_SLAB, 6, BlockDoubleStepAbstract.EnumStoneSlabVariant.SAND.a()), new Object[] { "###", Character.valueOf('#'), Blocks.SANDSTONE});
        this.registerShapedRecipe(new ItemStack(Blocks.STONE_SLAB, 6, BlockDoubleStepAbstract.EnumStoneSlabVariant.BRICK.a()), new Object[] { "###", Character.valueOf('#'), Blocks.BRICK_BLOCK});
        this.registerShapedRecipe(new ItemStack(Blocks.STONE_SLAB, 6, BlockDoubleStepAbstract.EnumStoneSlabVariant.SMOOTHBRICK.a()), new Object[] { "###", Character.valueOf('#'), Blocks.STONEBRICK});
        this.registerShapedRecipe(new ItemStack(Blocks.STONE_SLAB, 6, BlockDoubleStepAbstract.EnumStoneSlabVariant.NETHERBRICK.a()), new Object[] { "###", Character.valueOf('#'), Blocks.NETHER_BRICK});
        this.registerShapedRecipe(new ItemStack(Blocks.STONE_SLAB, 6, BlockDoubleStepAbstract.EnumStoneSlabVariant.QUARTZ.a()), new Object[] { "###", Character.valueOf('#'), Blocks.QUARTZ_BLOCK});
        this.registerShapedRecipe(new ItemStack(Blocks.STONE_SLAB2, 6, BlockDoubleStoneStepAbstract.EnumStoneSlab2Variant.RED_SANDSTONE.a()), new Object[] { "###", Character.valueOf('#'), Blocks.RED_SANDSTONE});
        this.registerShapedRecipe(new ItemStack(Blocks.PURPUR_SLAB, 6, 0), new Object[] { "###", Character.valueOf('#'), Blocks.PURPUR_BLOCK});
        this.registerShapedRecipe(new ItemStack(Blocks.WOODEN_SLAB, 6, 0), new Object[] { "###", Character.valueOf('#'), new ItemStack(Blocks.PLANKS, 1, BlockWood.EnumLogVariant.OAK.a())});
        this.registerShapedRecipe(new ItemStack(Blocks.WOODEN_SLAB, 6, BlockWood.EnumLogVariant.BIRCH.a()), new Object[] { "###", Character.valueOf('#'), new ItemStack(Blocks.PLANKS, 1, BlockWood.EnumLogVariant.BIRCH.a())});
        this.registerShapedRecipe(new ItemStack(Blocks.WOODEN_SLAB, 6, BlockWood.EnumLogVariant.SPRUCE.a()), new Object[] { "###", Character.valueOf('#'), new ItemStack(Blocks.PLANKS, 1, BlockWood.EnumLogVariant.SPRUCE.a())});
        this.registerShapedRecipe(new ItemStack(Blocks.WOODEN_SLAB, 6, BlockWood.EnumLogVariant.JUNGLE.a()), new Object[] { "###", Character.valueOf('#'), new ItemStack(Blocks.PLANKS, 1, BlockWood.EnumLogVariant.JUNGLE.a())});
        this.registerShapedRecipe(new ItemStack(Blocks.WOODEN_SLAB, 6, 4 + BlockWood.EnumLogVariant.ACACIA.a() - 4), new Object[] { "###", Character.valueOf('#'), new ItemStack(Blocks.PLANKS, 1, 4 + BlockWood.EnumLogVariant.ACACIA.a() - 4)});
        this.registerShapedRecipe(new ItemStack(Blocks.WOODEN_SLAB, 6, 4 + BlockWood.EnumLogVariant.DARK_OAK.a() - 4), new Object[] { "###", Character.valueOf('#'), new ItemStack(Blocks.PLANKS, 1, 4 + BlockWood.EnumLogVariant.DARK_OAK.a() - 4)});
        this.registerShapedRecipe(new ItemStack(Blocks.LADDER, 3), new Object[] { "# #", "###", "# #", Character.valueOf('#'), Items.STICK});
        this.registerShapedRecipe(new ItemStack(Items.WOODEN_DOOR, 3), new Object[] { "##", "##", "##", Character.valueOf('#'), new ItemStack(Blocks.PLANKS, 1, BlockWood.EnumLogVariant.OAK.a())});
        this.registerShapedRecipe(new ItemStack(Items.SPRUCE_DOOR, 3), new Object[] { "##", "##", "##", Character.valueOf('#'), new ItemStack(Blocks.PLANKS, 1, BlockWood.EnumLogVariant.SPRUCE.a())});
        this.registerShapedRecipe(new ItemStack(Items.BIRCH_DOOR, 3), new Object[] { "##", "##", "##", Character.valueOf('#'), new ItemStack(Blocks.PLANKS, 1, BlockWood.EnumLogVariant.BIRCH.a())});
        this.registerShapedRecipe(new ItemStack(Items.JUNGLE_DOOR, 3), new Object[] { "##", "##", "##", Character.valueOf('#'), new ItemStack(Blocks.PLANKS, 1, BlockWood.EnumLogVariant.JUNGLE.a())});
        this.registerShapedRecipe(new ItemStack(Items.ACACIA_DOOR, 3), new Object[] { "##", "##", "##", Character.valueOf('#'), new ItemStack(Blocks.PLANKS, 1, BlockWood.EnumLogVariant.ACACIA.a())});
        this.registerShapedRecipe(new ItemStack(Items.DARK_OAK_DOOR, 3), new Object[] { "##", "##", "##", Character.valueOf('#'), new ItemStack(Blocks.PLANKS, 1, BlockWood.EnumLogVariant.DARK_OAK.a())});
        this.registerShapedRecipe(new ItemStack(Blocks.TRAPDOOR, 2), new Object[] { "###", "###", Character.valueOf('#'), Blocks.PLANKS});
        this.registerShapedRecipe(new ItemStack(Items.IRON_DOOR, 3), new Object[] { "##", "##", "##", Character.valueOf('#'), Items.IRON_INGOT});
        this.registerShapedRecipe(new ItemStack(Blocks.IRON_TRAPDOOR, 1), new Object[] { "##", "##", Character.valueOf('#'), Items.IRON_INGOT});
        this.registerShapedRecipe(new ItemStack(Items.SIGN, 3), new Object[] { "###", "###", " X ", Character.valueOf('#'), Blocks.PLANKS, Character.valueOf('X'), Items.STICK});
        this.registerShapedRecipe(new ItemStack(Items.CAKE, 1), new Object[] { "AAA", "BEB", "CCC", Character.valueOf('A'), Items.MILK_BUCKET, Character.valueOf('B'), Items.SUGAR, Character.valueOf('C'), Items.WHEAT, Character.valueOf('E'), Items.EGG});
        this.registerShapedRecipe(new ItemStack(Items.SUGAR, 1), new Object[] { "#", Character.valueOf('#'), Items.REEDS});
        this.registerShapedRecipe(new ItemStack(Blocks.PLANKS, 4, BlockWood.EnumLogVariant.OAK.a()), new Object[] { "#", Character.valueOf('#'), new ItemStack(Blocks.LOG, 1, BlockWood.EnumLogVariant.OAK.a())});
        this.registerShapedRecipe(new ItemStack(Blocks.PLANKS, 4, BlockWood.EnumLogVariant.SPRUCE.a()), new Object[] { "#", Character.valueOf('#'), new ItemStack(Blocks.LOG, 1, BlockWood.EnumLogVariant.SPRUCE.a())});
        this.registerShapedRecipe(new ItemStack(Blocks.PLANKS, 4, BlockWood.EnumLogVariant.BIRCH.a()), new Object[] { "#", Character.valueOf('#'), new ItemStack(Blocks.LOG, 1, BlockWood.EnumLogVariant.BIRCH.a())});
        this.registerShapedRecipe(new ItemStack(Blocks.PLANKS, 4, BlockWood.EnumLogVariant.JUNGLE.a()), new Object[] { "#", Character.valueOf('#'), new ItemStack(Blocks.LOG, 1, BlockWood.EnumLogVariant.JUNGLE.a())});
        this.registerShapedRecipe(new ItemStack(Blocks.PLANKS, 4, 4 + BlockWood.EnumLogVariant.ACACIA.a() - 4), new Object[] { "#", Character.valueOf('#'), new ItemStack(Blocks.LOG2, 1, BlockWood.EnumLogVariant.ACACIA.a() - 4)});
        this.registerShapedRecipe(new ItemStack(Blocks.PLANKS, 4, 4 + BlockWood.EnumLogVariant.DARK_OAK.a() - 4), new Object[] { "#", Character.valueOf('#'), new ItemStack(Blocks.LOG2, 1, BlockWood.EnumLogVariant.DARK_OAK.a() - 4)});
        this.registerShapedRecipe(new ItemStack(Items.STICK, 4), new Object[] { "#", "#", Character.valueOf('#'), Blocks.PLANKS});
        this.registerShapedRecipe(new ItemStack(Blocks.TORCH, 4), new Object[] { "X", "#", Character.valueOf('X'), Items.COAL, Character.valueOf('#'), Items.STICK});
        this.registerShapedRecipe(new ItemStack(Blocks.TORCH, 4), new Object[] { "X", "#", Character.valueOf('X'), new ItemStack(Items.COAL, 1, 1), Character.valueOf('#'), Items.STICK});
        this.registerShapedRecipe(new ItemStack(Items.BOWL, 4), new Object[] { "# #", " # ", Character.valueOf('#'), Blocks.PLANKS});
        this.registerShapedRecipe(new ItemStack(Items.GLASS_BOTTLE, 3), new Object[] { "# #", " # ", Character.valueOf('#'), Blocks.GLASS});
        this.registerShapedRecipe(new ItemStack(Blocks.RAIL, 16), new Object[] { "X X", "X#X", "X X", Character.valueOf('X'), Items.IRON_INGOT, Character.valueOf('#'), Items.STICK});
        this.registerShapedRecipe(new ItemStack(Blocks.GOLDEN_RAIL, 6), new Object[] { "X X", "X#X", "XRX", Character.valueOf('X'), Items.GOLD_INGOT, Character.valueOf('R'), Items.REDSTONE, Character.valueOf('#'), Items.STICK});
        this.registerShapedRecipe(new ItemStack(Blocks.ACTIVATOR_RAIL, 6), new Object[] { "XSX", "X#X", "XSX", Character.valueOf('X'), Items.IRON_INGOT, Character.valueOf('#'), Blocks.REDSTONE_TORCH, Character.valueOf('S'), Items.STICK});
        this.registerShapedRecipe(new ItemStack(Blocks.DETECTOR_RAIL, 6), new Object[] { "X X", "X#X", "XRX", Character.valueOf('X'), Items.IRON_INGOT, Character.valueOf('R'), Items.REDSTONE, Character.valueOf('#'), Blocks.STONE_PRESSURE_PLATE});
        this.registerShapedRecipe(new ItemStack(Items.MINECART, 1), new Object[] { "# #", "###", Character.valueOf('#'), Items.IRON_INGOT});
        this.registerShapedRecipe(new ItemStack(Items.CAULDRON, 1), new Object[] { "# #", "# #", "###", Character.valueOf('#'), Items.IRON_INGOT});
        this.registerShapedRecipe(new ItemStack(Items.BREWING_STAND, 1), new Object[] { " B ", "###", Character.valueOf('#'), Blocks.COBBLESTONE, Character.valueOf('B'), Items.BLAZE_ROD});
        this.registerShapedRecipe(new ItemStack(Blocks.LIT_PUMPKIN, 1), new Object[] { "A", "B", Character.valueOf('A'), Blocks.PUMPKIN, Character.valueOf('B'), Blocks.TORCH});
        this.registerShapedRecipe(new ItemStack(Items.CHEST_MINECART, 1), new Object[] { "A", "B", Character.valueOf('A'), Blocks.CHEST, Character.valueOf('B'), Items.MINECART});
        this.registerShapedRecipe(new ItemStack(Items.FURNACE_MINECART, 1), new Object[] { "A", "B", Character.valueOf('A'), Blocks.FURNACE, Character.valueOf('B'), Items.MINECART});
        this.registerShapedRecipe(new ItemStack(Items.TNT_MINECART, 1), new Object[] { "A", "B", Character.valueOf('A'), Blocks.TNT, Character.valueOf('B'), Items.MINECART});
        this.registerShapedRecipe(new ItemStack(Items.HOPPER_MINECART, 1), new Object[] { "A", "B", Character.valueOf('A'), Blocks.HOPPER, Character.valueOf('B'), Items.MINECART});
        this.registerShapedRecipe(new ItemStack(Items.aH, 1), new Object[] { "# #", "###", Character.valueOf('#'), new ItemStack(Blocks.PLANKS, 1, BlockWood.EnumLogVariant.OAK.a())});
        this.registerShapedRecipe(new ItemStack(Items.aI, 1), new Object[] { "# #", "###", Character.valueOf('#'), new ItemStack(Blocks.PLANKS, 1, BlockWood.EnumLogVariant.SPRUCE.a())});
        this.registerShapedRecipe(new ItemStack(Items.aJ, 1), new Object[] { "# #", "###", Character.valueOf('#'), new ItemStack(Blocks.PLANKS, 1, BlockWood.EnumLogVariant.BIRCH.a())});
        this.registerShapedRecipe(new ItemStack(Items.aK, 1), new Object[] { "# #", "###", Character.valueOf('#'), new ItemStack(Blocks.PLANKS, 1, BlockWood.EnumLogVariant.JUNGLE.a())});
        this.registerShapedRecipe(new ItemStack(Items.aL, 1), new Object[] { "# #", "###", Character.valueOf('#'), new ItemStack(Blocks.PLANKS, 1, BlockWood.EnumLogVariant.ACACIA.a())});
        this.registerShapedRecipe(new ItemStack(Items.aM, 1), new Object[] { "# #", "###", Character.valueOf('#'), new ItemStack(Blocks.PLANKS, 1, BlockWood.EnumLogVariant.DARK_OAK.a())});
        this.registerShapedRecipe(new ItemStack(Items.BUCKET, 1), new Object[] { "# #", " # ", Character.valueOf('#'), Items.IRON_INGOT});
        this.registerShapedRecipe(new ItemStack(Items.FLOWER_POT, 1), new Object[] { "# #", " # ", Character.valueOf('#'), Items.BRICK});
        this.registerShapelessRecipe(new ItemStack(Items.FLINT_AND_STEEL, 1), new Object[] { new ItemStack(Items.IRON_INGOT, 1), new ItemStack(Items.FLINT, 1)});
        this.registerShapedRecipe(new ItemStack(Items.BREAD, 1), new Object[] { "###", Character.valueOf('#'), Items.WHEAT});
        this.registerShapedRecipe(new ItemStack(Blocks.OAK_STAIRS, 4), new Object[] { "#  ", "## ", "###", Character.valueOf('#'), new ItemStack(Blocks.PLANKS, 1, BlockWood.EnumLogVariant.OAK.a())});
        this.registerShapedRecipe(new ItemStack(Blocks.BIRCH_STAIRS, 4), new Object[] { "#  ", "## ", "###", Character.valueOf('#'), new ItemStack(Blocks.PLANKS, 1, BlockWood.EnumLogVariant.BIRCH.a())});
        this.registerShapedRecipe(new ItemStack(Blocks.SPRUCE_STAIRS, 4), new Object[] { "#  ", "## ", "###", Character.valueOf('#'), new ItemStack(Blocks.PLANKS, 1, BlockWood.EnumLogVariant.SPRUCE.a())});
        this.registerShapedRecipe(new ItemStack(Blocks.JUNGLE_STAIRS, 4), new Object[] { "#  ", "## ", "###", Character.valueOf('#'), new ItemStack(Blocks.PLANKS, 1, BlockWood.EnumLogVariant.JUNGLE.a())});
        this.registerShapedRecipe(new ItemStack(Blocks.ACACIA_STAIRS, 4), new Object[] { "#  ", "## ", "###", Character.valueOf('#'), new ItemStack(Blocks.PLANKS, 1, 4 + BlockWood.EnumLogVariant.ACACIA.a() - 4)});
        this.registerShapedRecipe(new ItemStack(Blocks.DARK_OAK_STAIRS, 4), new Object[] { "#  ", "## ", "###", Character.valueOf('#'), new ItemStack(Blocks.PLANKS, 1, 4 + BlockWood.EnumLogVariant.DARK_OAK.a() - 4)});
        this.registerShapedRecipe(new ItemStack(Items.FISHING_ROD, 1), new Object[] { "  #", " #X", "# X", Character.valueOf('#'), Items.STICK, Character.valueOf('X'), Items.STRING});
        this.registerShapedRecipe(new ItemStack(Items.CARROT_ON_A_STICK, 1), new Object[] { "# ", " X", Character.valueOf('#'), Items.FISHING_ROD, Character.valueOf('X'), Items.CARROT});
        this.registerShapedRecipe(new ItemStack(Blocks.STONE_STAIRS, 4), new Object[] { "#  ", "## ", "###", Character.valueOf('#'), Blocks.COBBLESTONE});
        this.registerShapedRecipe(new ItemStack(Blocks.BRICK_STAIRS, 4), new Object[] { "#  ", "## ", "###", Character.valueOf('#'), Blocks.BRICK_BLOCK});
        this.registerShapedRecipe(new ItemStack(Blocks.STONE_BRICK_STAIRS, 4), new Object[] { "#  ", "## ", "###", Character.valueOf('#'), Blocks.STONEBRICK});
        this.registerShapedRecipe(new ItemStack(Blocks.NETHER_BRICK_STAIRS, 4), new Object[] { "#  ", "## ", "###", Character.valueOf('#'), Blocks.NETHER_BRICK});
        this.registerShapedRecipe(new ItemStack(Blocks.SANDSTONE_STAIRS, 4), new Object[] { "#  ", "## ", "###", Character.valueOf('#'), Blocks.SANDSTONE});
        this.registerShapedRecipe(new ItemStack(Blocks.RED_SANDSTONE_STAIRS, 4), new Object[] { "#  ", "## ", "###", Character.valueOf('#'), Blocks.RED_SANDSTONE});
        this.registerShapedRecipe(new ItemStack(Blocks.QUARTZ_STAIRS, 4), new Object[] { "#  ", "## ", "###", Character.valueOf('#'), Blocks.QUARTZ_BLOCK});
        this.registerShapedRecipe(new ItemStack(Items.PAINTING, 1), new Object[] { "###", "#X#", "###", Character.valueOf('#'), Items.STICK, Character.valueOf('X'), Blocks.WOOL});
        this.registerShapedRecipe(new ItemStack(Items.ITEM_FRAME, 1), new Object[] { "###", "#X#", "###", Character.valueOf('#'), Items.STICK, Character.valueOf('X'), Items.LEATHER});
        this.registerShapedRecipe(new ItemStack(Items.GOLDEN_APPLE), new Object[] { "###", "#X#", "###", Character.valueOf('#'), Items.GOLD_INGOT, Character.valueOf('X'), Items.APPLE});
        this.registerShapedRecipe(new ItemStack(Items.GOLDEN_CARROT), new Object[] { "###", "#X#", "###", Character.valueOf('#'), Items.GOLD_NUGGET, Character.valueOf('X'), Items.CARROT});
        this.registerShapedRecipe(new ItemStack(Items.SPECKLED_MELON, 1), new Object[] { "###", "#X#", "###", Character.valueOf('#'), Items.GOLD_NUGGET, Character.valueOf('X'), Items.MELON});
        this.registerShapedRecipe(new ItemStack(Blocks.LEVER, 1), new Object[] { "X", "#", Character.valueOf('#'), Blocks.COBBLESTONE, Character.valueOf('X'), Items.STICK});
        this.registerShapedRecipe(new ItemStack(Blocks.TRIPWIRE_HOOK, 2), new Object[] { "I", "S", "#", Character.valueOf('#'), Blocks.PLANKS, Character.valueOf('S'), Items.STICK, Character.valueOf('I'), Items.IRON_INGOT});
        this.registerShapedRecipe(new ItemStack(Blocks.REDSTONE_TORCH, 1), new Object[] { "X", "#", Character.valueOf('#'), Items.STICK, Character.valueOf('X'), Items.REDSTONE});
        this.registerShapedRecipe(new ItemStack(Items.REPEATER, 1), new Object[] { "#X#", "III", Character.valueOf('#'), Blocks.REDSTONE_TORCH, Character.valueOf('X'), Items.REDSTONE, Character.valueOf('I'), new ItemStack(Blocks.STONE, 1, BlockStone.EnumStoneVariant.STONE.a())});
        this.registerShapedRecipe(new ItemStack(Items.COMPARATOR, 1), new Object[] { " # ", "#X#", "III", Character.valueOf('#'), Blocks.REDSTONE_TORCH, Character.valueOf('X'), Items.QUARTZ, Character.valueOf('I'), new ItemStack(Blocks.STONE, 1, BlockStone.EnumStoneVariant.STONE.a())});
        this.registerShapedRecipe(new ItemStack(Items.CLOCK, 1), new Object[] { " # ", "#X#", " # ", Character.valueOf('#'), Items.GOLD_INGOT, Character.valueOf('X'), Items.REDSTONE});
        this.registerShapedRecipe(new ItemStack(Items.COMPASS, 1), new Object[] { " # ", "#X#", " # ", Character.valueOf('#'), Items.IRON_INGOT, Character.valueOf('X'), Items.REDSTONE});
        this.registerShapedRecipe(new ItemStack(Items.MAP, 1), new Object[] { "###", "#X#", "###", Character.valueOf('#'), Items.PAPER, Character.valueOf('X'), Items.COMPASS});
        this.registerShapedRecipe(new ItemStack(Blocks.STONE_BUTTON, 1), new Object[] { "#", Character.valueOf('#'), new ItemStack(Blocks.STONE, 1, BlockStone.EnumStoneVariant.STONE.a())});
        this.registerShapedRecipe(new ItemStack(Blocks.WOODEN_BUTTON, 1), new Object[] { "#", Character.valueOf('#'), Blocks.PLANKS});
        this.registerShapedRecipe(new ItemStack(Blocks.STONE_PRESSURE_PLATE, 1), new Object[] { "##", Character.valueOf('#'), new ItemStack(Blocks.STONE, 1, BlockStone.EnumStoneVariant.STONE.a())});
        this.registerShapedRecipe(new ItemStack(Blocks.WOODEN_PRESSURE_PLATE, 1), new Object[] { "##", Character.valueOf('#'), Blocks.PLANKS});
        this.registerShapedRecipe(new ItemStack(Blocks.HEAVY_WEIGHTED_PRESSURE_PLATE, 1), new Object[] { "##", Character.valueOf('#'), Items.IRON_INGOT});
        this.registerShapedRecipe(new ItemStack(Blocks.LIGHT_WEIGHTED_PRESSURE_PLATE, 1), new Object[] { "##", Character.valueOf('#'), Items.GOLD_INGOT});
        this.registerShapedRecipe(new ItemStack(Blocks.DISPENSER, 1), new Object[] { "###", "#X#", "#R#", Character.valueOf('#'), Blocks.COBBLESTONE, Character.valueOf('X'), Items.BOW, Character.valueOf('R'), Items.REDSTONE});
        this.registerShapedRecipe(new ItemStack(Blocks.DROPPER, 1), new Object[] { "###", "# #", "#R#", Character.valueOf('#'), Blocks.COBBLESTONE, Character.valueOf('R'), Items.REDSTONE});
        this.registerShapedRecipe(new ItemStack(Blocks.dk, 1), new Object[] { "###", "RRQ", "###", Character.valueOf('#'), Blocks.COBBLESTONE, Character.valueOf('R'), Items.REDSTONE, Character.valueOf('Q'), Items.QUARTZ});
        this.registerShapedRecipe(new ItemStack(Blocks.PISTON, 1), new Object[] { "TTT", "#X#", "#R#", Character.valueOf('#'), Blocks.COBBLESTONE, Character.valueOf('X'), Items.IRON_INGOT, Character.valueOf('R'), Items.REDSTONE, Character.valueOf('T'), Blocks.PLANKS});
        this.registerShapedRecipe(new ItemStack(Blocks.STICKY_PISTON, 1), new Object[] { "S", "P", Character.valueOf('S'), Items.SLIME, Character.valueOf('P'), Blocks.PISTON});
        this.registerShapedRecipe(new ItemStack(Items.BED, 1), new Object[] { "###", "XXX", Character.valueOf('#'), Blocks.WOOL, Character.valueOf('X'), Blocks.PLANKS});
        this.registerShapedRecipe(new ItemStack(Blocks.ENCHANTING_TABLE, 1), new Object[] { " B ", "D#D", "###", Character.valueOf('#'), Blocks.OBSIDIAN, Character.valueOf('B'), Items.BOOK, Character.valueOf('D'), Items.DIAMOND});
        this.registerShapedRecipe(new ItemStack(Blocks.ANVIL, 1), new Object[] { "III", " i ", "iii", Character.valueOf('I'), Blocks.IRON_BLOCK, Character.valueOf('i'), Items.IRON_INGOT});
        this.registerShapedRecipe(new ItemStack(Items.LEATHER), new Object[] { "##", "##", Character.valueOf('#'), Items.RABBIT_HIDE});
        this.registerShapelessRecipe(new ItemStack(Items.ENDER_EYE, 1), new Object[] { Items.ENDER_PEARL, Items.BLAZE_POWDER});
        this.registerShapelessRecipe(new ItemStack(Items.FIRE_CHARGE, 3), new Object[] { Items.GUNPOWDER, Items.BLAZE_POWDER, Items.COAL});
        this.registerShapelessRecipe(new ItemStack(Items.FIRE_CHARGE, 3), new Object[] { Items.GUNPOWDER, Items.BLAZE_POWDER, new ItemStack(Items.COAL, 1, 1)});
        this.registerShapedRecipe(new ItemStack(Blocks.DAYLIGHT_DETECTOR), new Object[] { "GGG", "QQQ", "WWW", Character.valueOf('G'), Blocks.GLASS, Character.valueOf('Q'), Items.QUARTZ, Character.valueOf('W'), Blocks.WOODEN_SLAB});
        this.registerShapedRecipe(new ItemStack(Items.cQ), new Object[] { "GGG", "GEG", "GTG", Character.valueOf('G'), Blocks.GLASS, Character.valueOf('E'), Items.ENDER_EYE, Character.valueOf('T'), Items.GHAST_TEAR});
        this.registerShapedRecipe(new ItemStack(Blocks.HOPPER), new Object[] { "I I", "ICI", " I ", Character.valueOf('I'), Items.IRON_INGOT, Character.valueOf('C'), Blocks.CHEST});
        this.registerShapedRecipe(new ItemStack(Items.ARMOR_STAND, 1), new Object[] { "///", " / ", "/_/", Character.valueOf('/'), Items.STICK, Character.valueOf('_'), new ItemStack(Blocks.STONE_SLAB, 1, BlockDoubleStepAbstract.EnumStoneSlabVariant.STONE.a())});
        this.registerShapedRecipe(new ItemStack(Blocks.END_ROD, 4), new Object[] { "/", "#", Character.valueOf('/'), Items.BLAZE_ROD, Character.valueOf('#'), Items.CHORUS_FRUIT_POPPED});
        this.registerShapedRecipe(new ItemStack(Blocks.di, 1), new Object[] { "XXX", "XXX", "XXX", Character.valueOf('X'), new ItemStack(Items.DYE, 1, EnumColor.WHITE.getInvColorIndex())});
        sort();
    }

    // CraftBukkit start
    public void sort() {
       Collections.sort(this.recipes, new Comparator() {
            public int a(IRecipe irecipe, IRecipe irecipe1) {
                return irecipe instanceof ShapelessRecipes && irecipe1 instanceof ShapedRecipes ? 1 : (irecipe1 instanceof ShapelessRecipes && irecipe instanceof ShapedRecipes ? -1 : (irecipe1.a() < irecipe.a() ? -1 : (irecipe1.a() > irecipe.a() ? 1 : 0)));
            }

            @Override
			public int compare(Object object, Object object1) {
                return this.a((IRecipe) object, (IRecipe) object1);
            }
        });
    }

    public ShapedRecipes registerShapedRecipe(ItemStack itemstack, Object... aobject) {
        String s = "";
        int i = 0;
        int j = 0;
        int k = 0;
        int l;

        if (aobject[i] instanceof String[]) {
            String[] astring = ((String[]) aobject[i++]);
            String[] astring1 = astring;

            l = astring.length;

            for (int i1 = 0; i1 < l; ++i1) {
                String s1 = astring1[i1];

                ++k;
                j = s1.length();
                s = s + s1;
            }
        } else {
            while (aobject[i] instanceof String) {
                String s2 = (String) aobject[i++];

                ++k;
                j = s2.length();
                s = s + s2;
            }
        }

        Map hashmap;

        for (hashmap = HashObjObjMaps.newMutableMap(); i < aobject.length; i += 2) {
            Character character = (Character) aobject[i];
            ItemStack itemstack1 = ItemStack.a;

            if (aobject[i + 1] instanceof Item) {
                itemstack1 = new ItemStack((Item) aobject[i + 1]);
            } else if (aobject[i + 1] instanceof Block) {
                itemstack1 = new ItemStack((Block) aobject[i + 1], 1, 32767);
            } else if (aobject[i + 1] instanceof ItemStack) {
                itemstack1 = (ItemStack) aobject[i + 1];
            }

            hashmap.put(character, itemstack1);
        }

        ItemStack[] aitemstack = new ItemStack[j * k];

        for (l = 0; l < j * k; ++l) {
            char c0 = s.charAt(l);

            if (hashmap.containsKey(Character.valueOf(c0))) {
                aitemstack[l] = ((ItemStack) hashmap.get(Character.valueOf(c0))).cloneItemStack();
            } else {
                aitemstack[l] = ItemStack.a;
            }
        }

        ShapedRecipes shapedrecipes = new ShapedRecipes(j, k, aitemstack, itemstack);

        this.recipes.add(shapedrecipes);
        return shapedrecipes;
    }

    public void registerShapelessRecipe(ItemStack itemstack, Object... aobject) {
        ArrayList arraylist = Lists.newArrayList();
        Object[] aobject1 = aobject;
        int i = aobject.length;

        for (int j = 0; j < i; ++j) {
            Object object = aobject1[j];

            if (object instanceof ItemStack) {
                arraylist.add(((ItemStack) object).cloneItemStack());
            } else if (object instanceof Item) {
                arraylist.add(new ItemStack((Item) object));
            } else {
                if (!(object instanceof Block)) {
                    throw new IllegalArgumentException("Invalid shapeless recipe: unknown type " + object.getClass().getName() + "!");
                }

                arraylist.add(new ItemStack((Block) object));
            }
        }

        this.recipes.add(new ShapelessRecipes(itemstack, arraylist));
    }

    public void a(IRecipe irecipe) {
        this.recipes.add(irecipe);
    }

    public ItemStack craft(InventoryCrafting inventorycrafting, World world) {
        Iterator iterator = this.recipes.iterator();

        IRecipe irecipe;

        do {
            if (!iterator.hasNext()) {
                inventorycrafting.currentRecipe = null; // CraftBukkit - Clear recipe when no recipe is found
                return ItemStack.a;
            }

            irecipe = (IRecipe) iterator.next();
        } while (!irecipe.a(inventorycrafting, world));

        // CraftBukkit start - INVENTORY_PRE_CRAFT event
        inventorycrafting.currentRecipe = irecipe;
        ItemStack result = irecipe.craftItem(inventorycrafting);
        return CraftEventFactory.callPreCraftEvent(inventorycrafting, result, lastCraftView, false);
        // CraftBukkit end
    }

    public NonNullList<ItemStack> b(InventoryCrafting inventorycrafting, World world) {
        Iterator iterator = this.recipes.iterator();

        while (iterator.hasNext()) {
            IRecipe irecipe = (IRecipe) iterator.next();

            if (irecipe.a(inventorycrafting, world)) {
                return irecipe.b(inventorycrafting);
            }
        }

        NonNullList nonnulllist = NonNullList.a(inventorycrafting.getSize(), ItemStack.a);

        for (int i = 0; i < nonnulllist.size(); ++i) {
            nonnulllist.set(i, inventorycrafting.getItem(i));
        }

        return nonnulllist;
    }

    public List<IRecipe> getRecipes() {
        return this.recipes;
    }
}
