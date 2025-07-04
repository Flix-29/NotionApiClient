package de.flix29.notionApiClient.model.block.blockContent;

import de.flix29.notionApiClient.model.block.Block;

import java.util.List;

public sealed interface BlockContent permits Audio, Bookmark, Breadcrumb, BulletListItem, Callout, ChildDatabase, ChildPage,
        Code, Column, ColumnList, Divider, Embed, Equation, File, Heading, Image, LinkPreview, Mention, NumberedListItem,
        Paragraph, Pdf, Quote, SyncedBlock, Table, TableRow, TableOfContents, Template, ToDo, Toggle, Video {

    default BlockContent setChildren(List<Block> children) {
        throw new UnsupportedOperationException("Not supported for this block content type");
    }
}
