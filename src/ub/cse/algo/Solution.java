package ub.cse.algo;

import java.util.*;

public class Solution {

    private HashMap<Integer, ArrayList<Integer>> graph;

    public Solution(HashMap<Integer, ArrayList<Integer>> graph) {
        this.graph = graph;
    }

    public ArrayList<Integer> findCycle() {
        Stack<Integer> allnode = new Stack<>();
        allnode.push(0); // push startnode to stack

        ArrayList<Integer> stack = new ArrayList<>();

        int intarray[] = new int[graph.size()]; // Explored
        for (int i = 0; i < graph.size(); i++){
            intarray[i] = 0;
        }
        int discovered[] = new int[graph.size()]; // discovered
        for (int i = 0; i < graph.size(); i++){
            intarray[i] = 0;
        }
        discovered[0] = 1;

        Integer swith = 0;
        Integer cutposition = 0;

        while(allnode.size() != 0 && swith == 0){
            int key = allnode.pop();
            stack.add(key);

            if (intarray[key] == 0){
                intarray[key] = 1;
                for (Integer node : graph.get(key)){
                    if (intarray[node] == 0 && discovered[node] == 0){
                        allnode.push(node);
                        discovered[node] = 1;
                    }
//                    if (intarray[node] == 0 && discovered[node] == 1 ){ // the node we discovered but not explored
//
//                    }
                    if (intarray[node] == 1 && discovered[node] == 1){// the node we discovered and explored and at that time we find a cycle
                        if (stack.size() - (stack.indexOf(node) + 1) >= 2){
                            swith = 1;
                            cutposition = stack.indexOf(node);
                        }
                    }
                }
            }
        }
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = cutposition; i < stack.size(); i++){
            result.add(stack.get(i));
        }
        return result;
    }
}
