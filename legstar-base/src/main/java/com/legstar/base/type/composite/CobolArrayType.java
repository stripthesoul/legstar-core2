package com.legstar.base.type.composite;

import com.legstar.base.type.CobolType;
import com.legstar.base.utils.StringUtils;
import com.legstar.base.visitor.CobolVisitor;

/**
 * Array of other types.
 * <p/>
 * If there is no dependingOn then this is a fixed size array otherwise, it is a
 * variable size array which size is determined at runtime by the value of the
 * dependingOn variable.
 * 
 * @param an item java object type
 */
public class CobolArrayType extends CobolCompositeType {

    private final CobolType itemType;

    private final int maxOccurs;

    private final String dependingOn;

    /**
     * Maximum size in bytes.
     */
    private final int maxBytesLen;

    public CobolArrayType(CobolType itemType, int maxOccurs) {
        this(itemType, maxOccurs, null);
    }

    public CobolArrayType(CobolType itemType, int maxOccurs, String dependingOn) {
        this.maxOccurs = maxOccurs;
        this.itemType = itemType;
        this.dependingOn = dependingOn;
        this.maxBytesLen = maxOccurs * itemType.getMaxBytesLen();
    }

    /** {@inheritDoc} */
    public void accept(CobolVisitor visitor) {
        visitor.visit(this);
    }

    public CobolType getItemType() {
        return itemType;
    }

    public int getMaxOccurs() {
        return maxOccurs;
    }

    public String getDependingOn() {
        return dependingOn;
    }

    public boolean isVariableSize() {
        return StringUtils.isNotBlank(dependingOn);
    }

    /** {@inheritDoc} */
    public int getMaxBytesLen() {
        return maxBytesLen;
    }
}
