package org.broad.igv.aws.events;

public class SearchEvent extends Event {

    public SearchEvent(String searchString) {
//        TODO: parse searchString (extrace regions/chromosomes/etc)
        this.payload = searchString;
    }

    @Override
    public String getDetailType() {
        return "SearchEvent";
    }
}
