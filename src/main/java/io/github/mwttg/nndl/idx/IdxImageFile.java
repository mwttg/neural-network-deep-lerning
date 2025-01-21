package io.github.mwttg.nndl.idx;

import java.util.List;

public record IdxImageFile(int imageCount, int imageWidth, int imageHeight, List<int[]> images) {}
