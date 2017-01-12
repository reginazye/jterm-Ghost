/* Copyright 2016 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.engedu.ghost;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

public class TrieNode {
    // A map from the next character in the alphabet to the trie node containing those words
    private HashMap<Character, TrieNode> children;
    // If true, this node represents a complete word.
    private boolean isWord;

    public TrieNode() {
        children = new HashMap<>();
        isWord = false;
    }

    /**
     * Add the string as a child of this trie node.
     *
     * @param s String representing partial suffix of a word.
     */
    public void add(String s) {
        // TODO(you): add String s to this node.
        if (s.equals("")){
            isWord = true;
        } else {
            Character head = s.charAt(0);
            String rest = s.substring(1);
            if (!children.containsKey(head)){
                children.put(head, new TrieNode())
            }
            children.get(head).add(rest);
        }
    }

    /**
     * Determine whether this node is part of a complete word for the string.
     *
     * @param s String representing partial suffix of a word.
     * @return
     */
    public boolean isWord(String s) {
        // TODO(you): determine whether this node is part of a complete word for String s.
        if (s.length()==0){
            return isWord;
        }
        Character head = s.charAt(0);
        String rest = s.substring(1);
        if (!children.containsKey(head)) {
            return false;
        }
        return children.get(head).isWord(rest);
    }

    /**
     * Find any complete word with this partial segment.
     *
     * @param s String representing partial suffix of a word.
     * @return
     */
    public String getAnyWordStartingWith(String s) {
        // TODO(you):
        if (s.isEmpty()){
            return "";
        }else {
            Set<Character> keySet = children.keySet();
            if (keySet.isEmpty()) {
                return null;
            }
            Object[] validKids = keySet.toArray();
            int randomKid = (int) Math.floor(Math.random() *validKids.length);
            Character letter = (Character) validKids[randomKid];
            return letter + children.get(letter).getAnyWordStartingWith("");
        }
        else {
            Character head = s.charAt(0);
            String rest = s.substring(1);
            if (!children.containsKey(head)) {
                return null;
            }
            String word = children.get(head).getAnyWordStartingWith(rest);
            if (word == null) {
                return null;
            }
            return head+word;
        }
        return null;
    }

    /**
     * Find a good complete word with this partial segment.
     *
     * Definition of "good" left to implementor.
     *
     * @param s String representing partial suffix of a word.
     * @return
     */
    public String getGoodWordStartingWith(String s) {
        return null;
    }
}