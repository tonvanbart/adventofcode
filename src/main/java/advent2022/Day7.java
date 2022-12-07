package advent2022;

import lombok.Data;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.function.Predicate;

public class Day7 {
    public static void main(String[] args) throws Exception {
        new Day7().run();
    }

    private void run() throws Exception {
        var pfx = "src/main/resources/advent2022/day7";
        var file = "input";
        final var lines = Files.readAllLines(Path.of(pfx, file));
//        System.out.println("lines.size() = " + lines.size());
        Dir root = new Dir();
        Dir current = root;
        for (var line: lines) {
            if (line.startsWith("$ ")) {
                current = handleCommand(root, current, line.substring("$ ".length()));
            } else {
                handleItem(current, line);
            }
        }

        Set<Dir> select = new HashSet<>();
        satisfiesCondition(select, root, dir -> dir.getSize() < 100000);
        var theTotal = select.stream().map(Dir::getSize).reduce(Integer::sum).get();
        System.out.println("theTotal = " + theTotal);

        System.out.println("Outermost space="+root.getSize());
        var needed = 30000000 - (70000000 - root.getSize());
        System.out.println("needed = " + needed);

        Set<Dir> candidates = new HashSet<>();
        satisfiesCondition(candidates, root, dir -> dir.getSize() >= needed);
        System.out.println(candidates.size()+" candidates");
        for (var candidate: candidates) {
            System.out.println(candidate.getName() + "\t:" + candidate.getSize());
        }
        var candidate = candidates.stream().sorted(Comparator.comparing(Dir::getSize)).findFirst().get();
        System.out.println("candidate.getSize() = " + candidate.getSize());
    }

    Set<Dir> satisfiesCondition(Set<Dir> accum, Dir root, Predicate<Dir> condition) {
        for (Item item: root.contents.values()) {
            if (item instanceof Dir) {
                Dir dir = (Dir)item;
                if (condition.test(dir)) {
                    System.out.println("adding dir "+dir.getName()+" as it satisfies the condition");
                    accum.add(dir);
                }
                satisfiesCondition(accum, dir, condition);
            }
        }
        return accum;
    }

    private void handleItem(Dir current, String line) {
        if (line.startsWith("dir ")) {
            var name = line.substring("dir ".length());
            Dir sub = new Dir(current, name);
            current.contents.put(name, sub);
        } else {
            var parts = line.split(" ");
            var size = Integer.parseInt(parts[0]);
            Item item = new Item(parts[1], size);
            current.contents.put(item.name, item);
        }

    }

    private Dir handleCommand(Dir root, Dir current, String command) {
        var parts = command.split(" ");
        if (parts[0].equals("cd")) {
            if (parts[1].equals("..")) {
                current = current.parent;
            } else if (parts[1].equals("/")) {
                current = root;
            } else {
                current = (Dir) current.contents.get(parts[1]);
            }
        } else if (parts[0].equals("ls")) {
            // no action needed
        }
        return current;
    }

    @Data
    static class Item {
        Integer size;
        String name;
        public Item() {
        }

        public Item(String name, int size) {
            this.name = name;
            this.size = size;
        }

        @Override
        public String toString() {
            return  name + " (file, size=" + size +")";
        }
    }

    static class Dir extends Item {
        Map<String, Item> contents = new HashMap<>();
        Dir parent = null;
        public Dir() {
            this.name = "/";
        }

        public Dir(Dir parent, String name) {
            this.parent = parent;
            this.name = parent.name +"/" + name;
        }

        @Override
        public Integer getSize() {
            int result = 0;
            for (Item item: contents.values()) {
                result += item.getSize();
            }
//            System.out.println(this.name+" size:"+result);
            return result;
        }

        @Override
        public String toString() {
            String toString = name + " (dir)";
            for (var item: contents.values()) {
                toString = toString + "\n" + item.toString();
            }
            return toString;
        }
    }
}