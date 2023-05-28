package sagan.sh.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.jsoup.select.Evaluator;
import sagan.sh.character.CharacterId;
import sagan.sh.move.Move;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public final class Parser {

    private final Document doc;

    public Parser(String htmlDoc) {
        this.doc = Jsoup.parse(htmlDoc);
    }

    public List<Move> parse() {
        List<Move> moves = new ArrayList<>();
        Iterator<Element> tableIterator = this.doc.select("section#section-collapsible-1.section-collapsible > table.cargoDynamicTable.display").iterator();
        if (tableIterator.hasNext()) { moves.addAll(this.parseNormals(tableIterator.next())); }
        if (tableIterator.hasNext()) { moves.addAll(this.parseDriveThrow(tableIterator.next())); }
        if (tableIterator.hasNext()) { moves.addAll(this.parseSpecials(tableIterator.next())); }
        if (tableIterator.hasNext()) { moves.addAll(this.parseSupers(tableIterator.next())); }
        return moves;
    }

    private List<Move> parseNormals(Element table) {
        List<Move> moves = new ArrayList<>();
        Elements rowSet = table.select("tbody > tr");
        for (Element row : rowSet) {
            Elements dataValSet = row.select("td");
            Iterator<Element> iter = dataValSet.iterator();
            String input = safeNext(iter).map(Element::text).orElse("");
            String damage = safeNext(iter).map(Element::text).orElse("");
            String guard = safeNext(iter).map(Element::text).orElse("");
            String cancel = safeNext(iter).map(Element::text).orElse("");
            String startup = safeNext(iter).map(Element::text).orElse("");
            String active = safeNext(iter).map(Element::text).orElse("");
            String recovery = safeNext(iter).map(Element::text).orElse("");
            String hitAdv = safeNext(iter).map(Element::text).orElse("");
            String blockAdv = safeNext(iter).map(Element::text).orElse("");
            Move move = new Move(input, input, damage, guard, cancel, startup, active, recovery, hitAdv, blockAdv);
            moves.add(move);
        }
        return moves;
    }

    private List<Move> parseDriveThrow(Element table) {
        List<Move> moves = new ArrayList<>();
        Elements rowSet = table.select("tbody > tr");
        for (Element row : rowSet) {
            Elements dataValSet = row.select("td");
            Iterator<Element> iter = dataValSet.iterator();
            String name = safeNext(iter).map(Element::text).orElse("");
            String input = safeNext(iter).map(Element::text).orElse("");
            String damage = safeNext(iter).map(Element::text).orElse("");
            String guard = safeNext(iter).map(Element::text).orElse("");
            String cancel = safeNext(iter).map(Element::text).orElse("");
            String startup = safeNext(iter).map(Element::text).orElse("");
            String active = safeNext(iter).map(Element::text).orElse("");
            String recovery = safeNext(iter).map(Element::text).orElse("");
            String hitAdv = safeNext(iter).map(Element::text).orElse("");
            String blockAdv = safeNext(iter).map(Element::text).orElse("");
            Move move = new Move(name, input, damage, guard, cancel, startup, active, recovery, hitAdv, blockAdv);
            moves.add(move);
        }
        return moves;
    }

    private List<Move> parseSpecials(Element table) {
        List<Move> moves = new ArrayList<>();
        Elements rowSet = table.select("tbody > tr");
        for (Element row : rowSet) {
            Elements dataValSet = row.select("td");
            Iterator<Element> iter = dataValSet.iterator();
            String name = safeNext(iter).map(Element::text).orElse("");
            String input = safeNext(iter).map(Element::text).orElse("");
            String damage = safeNext(iter).map(Element::text).orElse("");
            String guard = safeNext(iter).map(Element::text).orElse("");
            String cancel = safeNext(iter).map(Element::text).orElse("");
            String startup = safeNext(iter).map(Element::text).orElse("");
            String active = safeNext(iter).map(Element::text).orElse("");
            String recovery = safeNext(iter).map(Element::text).orElse("");
            String hitAdv = safeNext(iter).map(Element::text).orElse("");
            String blockAdv = safeNext(iter).map(Element::text).orElse("");
            Move move = new Move(name, input, damage, guard, cancel, startup, active, recovery, hitAdv, blockAdv);
            moves.add(move);
        }
        return moves;
    }

    private List<Move> parseSupers(Element table) {
        List<Move> moves = new ArrayList<>();
        Elements rowSet = table.select("tbody > tr");
        for (Element row : rowSet) {
            Elements dataValSet = row.select("td");
            Iterator<Element> iter = dataValSet.iterator();
            String name = safeNext(iter).map(Element::text).orElse("");
            String input = safeNext(iter).map(Element::text).orElse("");
            String damage = safeNext(iter).map(Element::text).orElse("");
            String guard = safeNext(iter).map(Element::text).orElse("");
            String cancel = safeNext(iter).map(Element::text).orElse("");
            String startup = safeNext(iter).map(Element::text).orElse("");
            String active = safeNext(iter).map(Element::text).orElse("");
            String recovery = safeNext(iter).map(Element::text).orElse("");
            String hitAdv = safeNext(iter).map(Element::text).orElse("");
            String blockAdv = safeNext(iter).map(Element::text).orElse("");
            Move move = new Move(name, input, damage, guard, cancel, startup, active, recovery, hitAdv, blockAdv);
            moves.add(move);
        }
        return moves;
    }

    private static <T> Optional<T> safeNext(Iterator<T> iter) {
        Optional<T> val = Optional.empty();
        try {
            val = Optional.of(iter.next());
        } catch (NoSuchElementException ignored) {}
        return val;
    }
}
