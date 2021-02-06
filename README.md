# FastSmoothScroll
`fastSmoothScrollToPosition(int position)` extension for RecycleView

Suppose you have a list with many items. You are using `RecyclerView`. The app wants to select a certain item and also scroll to it to show it to you.

The item change would look like this:

```
// tell the adapter that the item changed, and it (but nothing else) needs to be redrawn if on screen
recyclerView.adapter.notifyItemChanged(position)
```

[RecyclerView](https://developer.android.com/guide/topics/ui/layout/recyclerview) is the standard tool for showing collections of data on the screen in Android, and [nofifyItemChanged](https://developer.android.com/reference/androidx/recyclerview/widget/RecyclerView.Adapter#notifyItemChanged(int)) is one of the optimisations that make it performance efficient and visually appealing. Let's see how well it works with scrolling...

Now you want to scroll to this item... we have 2 options:

## [smoothScrollToPosition](https://developer.android.com/reference/androidx/recyclerview/widget/RecyclerView#smoothScrollToPosition(int))?

As the name shows, a smooth scrolling animation provides is nice to look at and keeps us in the context. We visually see how we got to that item. But what if the list is long? It will scroll at a constant pace, through all the items in between, with a full render of each. This can get into a long and annoyng wait:

[![smoothScrollToPosition demo](https://raw.githubusercontent.com/rumburake/rumburake/main/smoothScrollToPosition.gif)

## [scrollToPosition](https://developer.android.com/reference/androidx/recyclerview/widget/RecyclerView#scrollToPosition(int))?

Unhappy with the long wait, we could give up the whole idea of animation. Just snap us to the item, a frame change should we quick, right? But no, it looks like any `scrollToPosition()` made after `notifyItemChanged()` results in some nasty animated curtain wipe effect or I don't know how to call it:

[![scrollToPosition demo](https://raw.githubusercontent.com/rumburake/rumburake/main/scrollToPosition.gif)

## fastSmoothScrollToPosition - what we really want

This new function offers a reasonable UX alternative to `scrollToPosition` and `smoothScrollToPosition`. It has some smoothness, and it doesn't take forever. The logic is:
 - if we are far away from the desired position, jump closer, within a few screen's coverage
 - then do a smooth scroll until we reach the position
 
[![fastSmoothScrollToPosition demo](https://raw.githubusercontent.com/rumburake/rumburake/main/fastSmoothScrollToPosition.gif)
