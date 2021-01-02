package net.theprogrammersworld.herobrine.network;

import java.util.Collections;

import com.comphenix.protocol.wrappers.EnumWrappers;
import com.comphenix.protocol.wrappers.PlayerInfoData;
import com.comphenix.protocol.wrappers.WrappedChatComponent;
import com.comphenix.protocol.wrappers.WrappedGameProfile;

import net.theprogrammersworld.herobrine.api.entity.IPlayerEntity;
import net.theprogrammersworld.herobrine.api.network.INetworkCore;
import net.theprogrammersworld.herobrine.api.network.wrapper.IWrapperPlayServerPlayerInfo;
import net.theprogrammersworld.herobrine.network.wrapper.WrapperPlayServerPlayerInfo;

public class NetworkCore implements INetworkCore {
    @Override
    public IWrapperPlayServerPlayerInfo createPlayerInfoPacket(IPlayerEntity entity, EnumWrappers.PlayerInfoAction action) {
        final IWrapperPlayServerPlayerInfo playerInfoPacket = new WrapperPlayServerPlayerInfo();

        final PlayerInfoData data = new PlayerInfoData(WrappedGameProfile.fromHandle(entity.getProfile().getGameProfile()),
                1, EnumWrappers.NativeGameMode.fromBukkit(entity.getGameMode()),
                WrappedChatComponent.fromText(entity.getName()));

        playerInfoPacket.setData(Collections.singletonList(data));
        playerInfoPacket.setAction(action);

        return playerInfoPacket;
    }
}
