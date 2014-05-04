package com.craftaga.agabacbone.concurrent;

import com.craftaga.agabacbone.commands.queue.CommandQueue;
import com.craftaga.agabacbone.session.IUserSession;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Recursive class that works with the command line input within minecraft
 *
 * This object should be set up at the start of the program and the command plus arguments
 * should be passed into getEmptyArgsCommandQueue then it should traverse the tree and return an
 * acceptable command to run
 *
 * @author Jonathan
 * @since 22/03/14
 */
public abstract class Method implements IMethod {
    private String name;
    private HashMap<String, IMethod> childMethods;
    protected IInstructionHandler instructionHandler;

    /**
     * Sets up class
     */
    public Method()
    {
        childMethods = new HashMap<String, IMethod>();
    }

    /**
     * validates that object has required fields before running operations that rely on Injected
     * fields
     *
     * @return Boolean is it valid
     */
    private Boolean validateObject()
    {
        return true;
    }

    /**
     * get the count of child methods added to this object
     *
     * @return method count
     */
    @Override
    public int getMethodCount() {
        return childMethods.size();
    }

    /**
     * adds a child method
     *
     * @param method child method
     */
    @Override
    public void addMethod(final IMethod method)
    {
        childMethods.put(method.getName(), method);
    }

    /**
     * get the name of the Method also acts as the string that is match on the command input
     * in minecraft                         stop
     *
     * @return name
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * set teh name, also acts as the string that is match on the command input
     * in minecraft
     *
     * @param name
     */
    @Override
    public IMethod setName(String name) {
        this.name = name.toLowerCase();
        return this;
    }

    /**
     * Gets a commandQueue ready to be passed into a threaded envoriment
     *
     * @param args command plus agrs from onCommand
     * @return commandQueue
     */
    @Override
    public CommandQueue getCommandQueue(final String[] args, final IUserSession userSession)
    {
        IInstructionHandler instructionHandlerToReturn = getInstructionHandler(userSession);
        if (validateObject()) {
            if(args.length == 0) {
                return instructionHandlerToReturn.getEmptyArgsCommandQueue();
            }
            if(childMethods.containsKey(args[0].toLowerCase())) {
                IMethod childMethod = childMethods.get(args[0]);
                return childMethod.getCommandQueue(Arrays.copyOfRange(args, 1, args.length), userSession);
            }
            if(instructionHandlerToReturn.getParameterBag().getRequiredParameterCount() <= args.length &&
                    instructionHandlerToReturn.getParameterBag().getParameterCount() >= args.length) {
                int x = 0;
                IParameterBag parameterBag = instructionHandlerToReturn.getParameterBag();
                for (IParameter parameter : instructionHandlerToReturn.getParameterBag()) {
                    parameterBag.getParameter(parameter.getName()).setValue(args[x]);
                    x++;
                    if(x >= args.length) {
                        break;
                    }
                }
                return instructionHandlerToReturn.getArgsCommandQueue();
            }
            return instructionHandlerToReturn.getInvalidUsageCommandQueue();
        }
        return null;
    }

    /**
     * Getter method to get all child methods
     *
     * @return child methods
     */
    public HashMap<String, IMethod> getChildMethods() {
        return childMethods;
    }

    /**
     * Setter to set all child methods
     *
     * @param childMethods child methods
     */
    public void setChildMethods(HashMap<String, IMethod> childMethods) {
        this.childMethods = childMethods;
    }
}
