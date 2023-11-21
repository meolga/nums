package com.example.myapplication;

import com.example.myapplication.utils.Chain;
import com.example.myapplication.utils.Chains;
import com.example.myapplication.utils.ChainsContainer;
import com.example.myapplication.utils.Element;
import com.example.myapplication.utils.MaskField;
import com.example.myapplication.utils.Position;
import com.example.myapplication.utils.SearchDirection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class SearchChainsImpl implements SearchChains {

    final static int MIN_CHAIN_SIZE = 3;
    int MAX;

    ChainsContainer container = new ChainsContainer();

    MaskField maskField;

    @Override
    public ChainsContainer search(int[][] field, int max) {
        this.MAX = max;
        container.clear();

        maskField = new MaskField(max);

        fetchChains(field);

        return container;
    }

    private void fetchChains(int[][] field) {
        for (int y = 0; y < MAX; y++) {
            for (int x = 0; x < MAX; x++) {
                Position pos = new Position(x, y);

                //do not take as first element in the chain if it already been used.
                if (maskField.isOccupied(pos)) {
                    continue;
                }

                Element element = new Element(field[y][x], pos);
                Chains chains = new Chains();
                maskField.setTempMark(pos);

                for (SearchDirection direction : findDirection(field, MAX, element, SearchDirection.NONE)) {
                    Chain c = lookChains(field, pos, direction, chains);
                    checkAndAdd(chains, c);
                }

                if (chains.size() != 0) {
                    container.add(chains);
                }
                maskField.clearBusy();
            }
        }
    }

    private Chain lookChains(int[][] field, Position pos, SearchDirection direction, Chains chains) {
        //go thru the chain and try to find all xing chains

        Chain tempChain = new Chain();
        Element element = new Element(field[pos.getY()][pos.getX()], pos);

        tempChain.add(element);

        for (FieldIteratorWithDirection it = new FieldIteratorWithDirection(field, MAX, direction, pos); it.hasNext(); ) {
            Element e = it.next();
            tempChain.add(e);

            maskField.setTempMark(pos);

            List<SearchDirection> directions = findDirection(field, MAX, e, direction);
            if (!isDirectionsOpposit(directions)) {
                for (SearchDirection sub_directions : directions) {
                    Chain c = lookChains(field, e.getPos(), sub_directions, chains);
                    checkAndAdd(chains, c);
                }
            } else {
                //special case: we should join 2parts and then check
                Chain joint = new Chain();
                for (SearchDirection sub_directions : directions) {
                    joint.add(lookChains(field, e.getPos(), sub_directions, chains));
                }
                joint.remove(0);// remove duplicated element
                checkAndAdd(chains, joint);
            }
        }

        return tempChain;
    }

    private boolean isDirectionsOpposit(List<SearchDirection> directions) {
        if (directions.size() != 2) {
            return false;
        }

        SearchDirection first = directions.get(0);
        SearchDirection second = directions.get(1);

        if ((first == SearchDirection.UP || first == SearchDirection.DOWN) && (second == SearchDirection.DOWN || second == SearchDirection.UP)) {
            return true;
        } else
            return (first == SearchDirection.LEFT || first == SearchDirection.RIGHT) && (second == SearchDirection.RIGHT || second == SearchDirection.LEFT);
    }

    private void checkAndAdd(Chains chains, Chain c) {
        if (c.size() >= MIN_CHAIN_SIZE) {
            chains.add(c);
            for (Element elem : c.getElentList()) {
                maskField.setOccupied(elem.getPos());
            }
        }
    }

    private List<SearchDirection> findDirection(int[][] field, int max, Element currentElement, SearchDirection direction) {

        int x = currentElement.getPos().getX();
        int y = currentElement.getPos().getY();
        int value = currentElement.getValue();

        List<SearchDirection> ret = new ArrayList<>();

        if (direction == SearchDirection.NONE) {
            if (x + 1 < max && value == field[y][x + 1] && !maskField.isOccupied(x + 1, y)) {
                ret.add(SearchDirection.RIGHT);
            }

            if (y + 1 < max && value == field[y + 1][x] && !maskField.isOccupied(x, y + 1)) {
                ret.add(SearchDirection.DOWN);
            }
        } else if (direction == SearchDirection.RIGHT || direction == SearchDirection.LEFT) {

            if (y - 1 >= 0 && value == field[y - 1][x] && !maskField.isOccupied(x, y - 1)) {
                ret.add(SearchDirection.UP);
            }

            if (y + 1 < max && value == field[y + 1][x] && !maskField.isOccupied(x, y + 1)) {
                ret.add(SearchDirection.DOWN);
            }
        } else if (direction == SearchDirection.UP || direction == SearchDirection.DOWN) {
            if (x - 1 >= 0 && value == field[y][x - 1] && !maskField.isOccupied(x - 1, y)) {
                ret.add(SearchDirection.LEFT);
            }

            if (x + 1 < max && value == field[y][x + 1] && !maskField.isOccupied(x + 1, y)) {
                ret.add(SearchDirection.RIGHT);
            }
        }

        return ret;
    }
}

class FieldIteratorWithDirection implements Iterator<Element> {

    private final int[][] field;
    private final int max;
    private final SearchDirection direction;

    private final Position currentPosition;

    FieldIteratorWithDirection(int[][] field, int max, SearchDirection direction, Position pos) {
        this.currentPosition = new Position(pos);
        this.field = field;
        this.max = max;
        this.direction = direction;
    }

    @Override
    public boolean hasNext() {
        int x = currentPosition.getX();
        int y = currentPosition.getY();
        int value = field[y][x];
        switch (direction) {
            case UP:
                if (currentPosition.getY() - 1 >= 0 && field[y - 1][x] == value) {
                    return true;
                }
                break;
            case DOWN:
                if (currentPosition.getY() + 1 < max && field[y + 1][x] == value) {
                    return true;
                }
                break;
            case LEFT:
                if (currentPosition.getX() - 1 >= 0 && field[y][x - 1] == value) {
                    return true;
                }
                break;
            case RIGHT:
                if (currentPosition.getX() + 1 < max && field[y][x + 1] == value) {
                    return true;
                }
                break;
            case NONE:
            default:
                return false;
        }
        return false;
    }

    @Override
    public Element next() {

        switch (direction) {
            case UP:
                if (hasNext()) {
                    currentPosition.setY(currentPosition.getY() - 1);
                    int value = field[currentPosition.getY()][currentPosition.getX()];
                    return new Element(value, new Position(currentPosition));
                }
                break;
            case DOWN: {
                if (hasNext()) {
                    currentPosition.setY(currentPosition.getY() + 1);
                    int value = field[currentPosition.getY()][currentPosition.getX()];
                    return new Element(value, new Position(currentPosition));
                }
                break;
            }
            case LEFT: {
                if (hasNext()) {
                    currentPosition.setX(currentPosition.getX() - 1);
                    int value = field[currentPosition.getY()][currentPosition.getX()];
                    return new Element(value, new Position(currentPosition));
                }
                break;
            }
            case RIGHT: {
                if (hasNext()) {
                    currentPosition.setX(currentPosition.getX() + 1);
                    int value = field[currentPosition.getY()][currentPosition.getX()];
                    return new Element(value, new Position(currentPosition));
                }
                break;
            }
            case NONE:
            default:
                break;

        }
        return null;
    }
}
