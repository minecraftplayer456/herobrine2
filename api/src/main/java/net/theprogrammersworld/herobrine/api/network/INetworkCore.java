package net.theprogrammersworld.herobrine.api.network;

import com.comphenix.protocol.wrappers.EnumWrappers;

import net.theprogrammersworld.herobrine.api.entity.IPlayerEntity;
import net.theprogrammersworld.herobrine.api.network.wrapper.IWrapperPlayServerPlayerInfo;

public interface INetworkCore {

    IWrapperPlayServerPlayerInfo createPlayerInfoPacket(IPlayerEntity entity, EnumWrappers.PlayerInfoAction action);
}
