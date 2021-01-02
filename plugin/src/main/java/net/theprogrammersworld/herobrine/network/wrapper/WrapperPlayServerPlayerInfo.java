package net.theprogrammersworld.herobrine.network.wrapper;

import java.util.List;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.wrappers.EnumWrappers.PlayerInfoAction;
import com.comphenix.protocol.wrappers.PlayerInfoData;

import net.theprogrammersworld.herobrine.api.network.wrapper.IWrapperPlayServerPlayerInfo;

public class WrapperPlayServerPlayerInfo extends AbstractPacket implements IWrapperPlayServerPlayerInfo {
    public static final PacketType TYPE = PacketType.Play.Server.PLAYER_INFO;

    public WrapperPlayServerPlayerInfo() {
        super("PlayerServerPlayerInfo", TYPE);
        getModifier().writeDefaults();
    }

    @Override
    public PlayerInfoAction getAction() {
        return getPlayerInfoAction().read(0);
    }

    @Override
    public void setAction(PlayerInfoAction value) {
        getPlayerInfoAction().write(0, value);
    }

    @Override
    public List<PlayerInfoData> getData() {
        return getPlayerInfoDataLists().read(0);
    }

    @Override
    public void setData(List<PlayerInfoData> value) {
        getPlayerInfoDataLists().write(0, value);
    }
}