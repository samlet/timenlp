package com.samlet.bridge;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import py4j.CallbackClient;
import py4j.GatewayServer;

import java.net.InetAddress;
import java.net.UnknownHostException;

import static py4j.GatewayServer.*;

public class ApplicaEntryPoint {

    private Stack stack;
    private Injector injector;

    public ApplicaEntryPoint() {
        stack = new Stack();
        stack.push("Initial Item");

        this.injector=Guice.createInjector(new AbstractModule() {
            @Override
            protected void configure() {
                super.configure();

            }
        });
    }

    public Stack getStack() {
        return stack;
    }
    public TimeAnalyst getTimeAnalyst(){
        return injector.getInstance(TimeAnalyst.class);
    }

    public static void main(String[] args) throws UnknownHostException {
        // entryPoint, port, defaultAddress(), connectTimeout, readTimeout, customCommands,
        //	 new CallbackClient(pythonPort, defaultAddress()), ServerSocketFactory.getDefault()
        InetAddress defaultAddress=InetAddress.getByName("0.0.0.0");
        GatewayServer gatewayServer = new GatewayServer(
                new ApplicaEntryPoint(),
                DEFAULT_PORT, defaultAddress,
                DEFAULT_CONNECT_TIMEOUT, DEFAULT_READ_TIMEOUT, null,
                new CallbackClient(DEFAULT_PYTHON_PORT, defaultAddress));
        gatewayServer.start();
        System.out.println("Gateway Server Started");
    }

}