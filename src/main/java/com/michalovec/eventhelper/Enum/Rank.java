package com.michalovec.eventhelper.Enum;

import org.bukkit.ChatColor;

public enum Rank {

    ADMIN(ChatColor.LIGHT_PURPLE + "ᴀᴅᴍɪɴ", new String[]{}),
    HRAC(ChatColor.GRAY + "ʜʀᴀᴄ", new String[]{}),
    BLUE(ChatColor.BLUE + "ʙʟᴜᴇ", new String[]{}),
    RED(ChatColor.RED + "ʀᴇᴅ", new String[]{}),
    GREEN(ChatColor.GREEN + "ɢʀᴇᴇɴ", new String[]{}),
    YELLOW(ChatColor.YELLOW + "ʏᴇʟʟᴏᴡ", new String[]{});

    private final String display;
    private final String[] permissions;

    Rank(String display, String[] permissions){
        this.display = display;
        this.permissions = permissions;
    }

    public String getDisplay() { return display; }

    public String[] getPermissions() { return permissions; }




}
