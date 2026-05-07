//package com.hhy.dreamingfishcore.server.rank.capability;
//
//import com.hhy.dreamingfishcore.server.rank.Rank;
//import com.hhy.dreamingfishcore.server.rank.RankRegistry;
//import net.minecraft.nbt.CompoundTag;
//
//public interface IRankCapability {
//    Rank getRank();
//    void setRank(Rank rank);
//    default void clearRank() {
//        setRank(RankRegistry.NO_RANK);
//    }
//    // NBT序列化
//    CompoundTag serializeNBT();
//    // NBT反序列化
//    void deserializeNBT(CompoundTag nbt);
//}