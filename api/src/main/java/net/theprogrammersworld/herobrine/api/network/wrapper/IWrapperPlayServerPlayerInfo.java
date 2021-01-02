package net.theprogrammersworld.herobrine.api.network.wrapper;

import java.util.List;

import com.comphenix.protocol.wrappers.EnumWrappers;
import com.comphenix.protocol.wrappers.PlayerInfoData;

public interface IWrapperPlayServerPlayerInfo extends IPacket {
    EnumWrappers.PlayerInfoAction getAction();

    void setAction(EnumWrappers.PlayerInfoAction action);

    List<PlayerInfoData> getData();

    void setData(List<PlayerInfoData> data);
}
