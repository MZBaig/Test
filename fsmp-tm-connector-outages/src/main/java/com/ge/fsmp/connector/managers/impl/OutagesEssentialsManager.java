package com.ge.fsmp.connector.managers.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ge.fsmp.connector.managers.IOutageEssentialsManager;
import com.ge.fsmp.connector.ras.IOutagesEssentialsReader;

@Service
public class OutagesEssentialsManager implements IOutageEssentialsManager {
    @Autowired
    IOutagesEssentialsReader outagesEssentialsReader;

    @Override
    public Map<String, Object> getOutagesEssentials() {
        return outagesEssentialsReader.getOutagesEssentials();
    }
}
