/* -*- Mode: Java; c-basic-offset: 4; tab-width: 20; indent-tabs-mode: nil; -*-
 * Copyright 2018 Mozilla
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use
 * this file except in compliance with the License. You may obtain a copy of the
 * License at http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software distributed
 * under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR
 * CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License. */

package org.mozilla.mentat;

import android.util.Log;

import com.sun.jna.Pointer;

import java.util.Date;
import java.util.UUID;

/**
 * This class wraps a raw pointer that points to a Rust `InProgressBuilder` object.
 *
 * {@link InProgressBuilder} provides a programmatic interface to performing assertions for entities.
 * It provides functions for adding and retracting values for attributes for an entity within
 * an in progress transaction.
 * <p/>
 * The `transact` function will transact the assertions that have been added to the {@link InProgressBuilder}
 * and pass back the {@link TxReport} that was generated by this transact and the {@link InProgress} that was
 * used to perform the transact. This enables you to perform further transacts on the same {@link InProgress}
 * before committing.
 * <p/>
 * <pre>{@code
 * long aEntid = txReport.getEntidForTempId("a");
 * long bEntid = txReport.getEntidForTempId("b");
 * InProgressBuilder builder = mentat.getEntityBuilder();
 * builder.add(aEntid, ":foo/boolean", true);
 * builder.add(bEntid, ":foo/instant", newDate);
 * InProgress inProgress = builder.transact().getInProgress();
 * inProgress.transact("[[:db/add \(aEntid) :foo/long 22]]");
 * inProgress.commit();
 * }</pre>
 * <p/>
 * The `commit` function will transact and commit the assertions that have been added to the
 * {@link InProgressBuilder}.
 * It will consume the {@link InProgress} used to perform the transact. It returns the {@link TxReport}
 * generated by the transact. After calling `commit`, a new transaction must be started by calling
 * <pre>Mentat.beginTransaction()</pre> in order to perform further actions.
 * <p/>
 * <pre>{@code
 * long aEntid = txReport.getEntidForTempId("a");
 * long bEntid = txReport.getEntidForTempId("b");
 * InProgressBuilder builder = mentat.getEntityBuilder();
 * builder.add(aEntid, ":foo/boolean", true);
 * builder.add(bEntid, ":foo/instant", newDate);
 * builder.commit();
 * }</pre>
 */
public class InProgressBuilder extends RustObject<JNA.InProgressBuilder> {

    public InProgressBuilder(JNA.InProgressBuilder pointer) {
        super(pointer);
    }
    /**
     * Asserts the value of attribute `keyword` to be the provided `value`.
     *
     * // TODO throw exception if error occurs
     *
     * @param entid   The `Entid` of the entity to be touched.
     * @param keyword   The name of the attribute in the format `:namespace/name`.
     * @param value The value to be asserted
     */
    public void add(long entid, String keyword, long value) {
        RustError.ByReference error = new RustError.ByReference();
        JNA.INSTANCE.in_progress_builder_add_long(this.validPointer(), entid, keyword, value, error);
        error.logAndConsumeError("InProgressBuilder");
    }

    /**
     * Asserts the value of attribute `keyword` to be the provided `value`.
     * // TODO throw exception if error occurs
     *
     * @param entid   The `Entid` of the entity to be touched.
     * @param keyword   The name of the attribute in the format `:namespace/name`.
     * @param value The value to be asserted
     */
    public void addRef(long entid, String keyword, long value) {
        RustError.ByReference error = new RustError.ByReference();
        JNA.INSTANCE.in_progress_builder_add_ref(this.validPointer(), entid, keyword, value, error);
        error.logAndConsumeError("InProgressBuilder");
    }

    /**
     * Asserts the value of attribute `keyword` to be the provided `value`.
     * // TODO throw exception if error occurs
     *
     * @param entid   The `Entid` of the entity to be touched.
     * @param keyword   The name of the attribute in the format `:namespace/name`.
     * @param value The value to be asserted
     */
    public void addKeyword(long entid, String keyword, String value) {
        RustError.ByReference error = new RustError.ByReference();
        JNA.INSTANCE.in_progress_builder_add_keyword(this.validPointer(), entid, keyword, value, error);
        error.logAndConsumeError("InProgressBuilder");
    }

    /**
     * Asserts the value of attribute `keyword` to be the provided `value`.
     * // TODO throw exception if error occurs
     *
     * @param entid   The `Entid` of the entity to be touched.
     * @param keyword   The name of the attribute in the format `:namespace/name`.
     * @param value The value to be asserted
     */
    public void add(long entid, String keyword, boolean value) {
        RustError.ByReference error = new RustError.ByReference();
        JNA.INSTANCE.in_progress_builder_add_boolean(this.validPointer(), entid, keyword, value ? 1 : 0, error);
        error.logAndConsumeError("InProgressBuilder");
    }

    /**
     * Asserts the value of attribute `keyword` to be the provided `value`.
     * // TODO throw exception if error occurs
     *
     * @param entid   The `Entid` of the entity to be touched.
     * @param keyword   The name of the attribute in the format `:namespace/name`.
     * @param value The value to be asserted
     */
    public void add(long entid, String keyword, double value) {
        RustError.ByReference error = new RustError.ByReference();
        JNA.INSTANCE.in_progress_builder_add_double(this.validPointer(), entid, keyword, value, error);
        error.logAndConsumeError("InProgressBuilder");
    }

    /**
     * Asserts the value of attribute `keyword` to be the provided `value`.
     * // TODO throw exception if error occurs
     *
     * @param entid   The `Entid` of the entity to be touched.
     * @param keyword   The name of the attribute in the format `:namespace/name`.
     * @param value The value to be asserted
     */
    public void add(long entid, String keyword, Date value) {
        RustError.ByReference error = new RustError.ByReference();
        JNA.INSTANCE.in_progress_builder_add_timestamp(this.validPointer(), entid, keyword, value.getTime() * 1_000, error);
        error.logAndConsumeError("InProgressBuilder");
    }

    /**
     * Asserts the value of attribute `keyword` to be the provided `value`.
     * // TODO throw exception if error occurs
     *
     * @param entid   The `Entid` of the entity to be touched.
     * @param keyword   The name of the attribute in the format `:namespace/name`.
     * @param value The value to be asserted
     */
    public void add(long entid, String keyword, String value) {
        RustError.ByReference error = new RustError.ByReference();
        JNA.INSTANCE.in_progress_builder_add_string(this.validPointer(), entid, keyword, value, error);
        error.logAndConsumeError("InProgressBuilder");
    }

    /**
     * Asserts the value of attribute `keyword` to be the provided `value`.
     * // TODO throw exception if error occurs
     *
     * @param entid   The `Entid` of the entity to be touched.
     * @param keyword   The name of the attribute in the format `:namespace/name`.
     * @param value The value to be asserted
     */
    public void add(long entid, String keyword, UUID value) {
        RustError.ByReference error = new RustError.ByReference();
        JNA.INSTANCE.in_progress_builder_add_uuid(this.validPointer(), entid, keyword, getPointerForUUID(value), error);
        error.logAndConsumeError("InProgressBuilder");
    }

    /**
     * Retracts the value of attribute `keyword` from the provided `value`.
     *
     * TODO throw exception if error occurs
     *
     * @param entid   The `Entid` of the entity to be touched.
     * @param keyword   The name of the attribute in the format `:namespace/name`.
     * @param value The value to be retracted
     */
    public void retract(long entid, String keyword, long value) {
        RustError.ByReference error = new RustError.ByReference();
        JNA.INSTANCE.in_progress_builder_retract_long(this.validPointer(), entid, keyword, value, error);
        error.logAndConsumeError("InProgressBuilder");
    }


    /**
     * Retracts the value of attribute `keyword` from the provided `value`.
     *
     * TODO throw exception if error occurs
     *
     * @param entid   The `Entid` of the entity to be touched.
     * @param keyword   The name of the attribute in the format `:namespace/name`.
     * @param value The value to be retracted
     */
    public void retractRef(long entid, String keyword, long value) {
        RustError.ByReference error = new RustError.ByReference();
        JNA.INSTANCE.in_progress_builder_retract_ref(this.validPointer(), entid, keyword, value, error);
        error.logAndConsumeError("InProgressBuilder");
    }

    /**
     * Retracts the value of attribute `keyword` from the provided `value`.
     *
     * TODO throw exception if error occurs
     *
     * @param entid   The `Entid` of the entity to be touched.
     * @param keyword   The name of the attribute in the format `:namespace/name`.
     * @param value The value to be retracted
     */
    public void retractKeyword(long entid, String keyword, String value) {
        RustError.ByReference error = new RustError.ByReference();
        JNA.INSTANCE.in_progress_builder_retract_keyword(this.validPointer(), entid, keyword, value, error);
        error.logAndConsumeError("InProgressBuilder");
    }

    /**
     * Retracts the value of attribute `keyword` from the provided `value`.
     *
     * TODO throw exception if error occurs
     *
     * @param entid   The `Entid` of the entity to be touched.
     * @param keyword   The name of the attribute in the format `:namespace/name`.
     * @param value The value to be retracted
     */
    public void retract(long entid, String keyword, boolean value) {
        RustError.ByReference error = new RustError.ByReference();
        JNA.INSTANCE.in_progress_builder_retract_boolean(this.validPointer(), entid, keyword, value ? 1 : 0, error);
        error.logAndConsumeError("InProgressBuilder");
    }

    /**
     * Retracts the value of attribute `keyword` from the provided `value`.
     *
     * TODO throw exception if error occurs
     *
     * @param entid   The `Entid` of the entity to be touched.
     * @param keyword   The name of the attribute in the format `:namespace/name`.
     * @param value The value to be retracted
     */
    public void retract(long entid, String keyword, double value) {
        RustError.ByReference error = new RustError.ByReference();
        JNA.INSTANCE.in_progress_builder_retract_double(this.validPointer(), entid, keyword, value, error);
        error.logAndConsumeError("InProgressBuilder");
    }

    /**
     * Retracts the value of attribute `keyword` from the provided `value`.
     *
     * TODO throw exception if error occurs
     *
     * @param entid   The `Entid` of the entity to be touched.
     * @param keyword   The name of the attribute in the format `:namespace/name`.
     * @param value The value to be retracted
     */
    public void retract(long entid, String keyword, Date value) {
        RustError.ByReference error = new RustError.ByReference();
        JNA.INSTANCE.in_progress_builder_retract_timestamp(this.validPointer(), entid, keyword, value.getTime() * 1_000, error);
        error.logAndConsumeError("InProgressBuilder");
    }

    /**
     * Retracts the value of attribute `keyword` from the provided `value`.
     *
     * TODO throw exception if error occurs
     *
     * @param entid   The `Entid` of the entity to be touched.
     * @param keyword   The name of the attribute in the format `:namespace/name`.
     * @param value The value to be retracted
     */
    public void retract(long entid, String keyword, String value) {
        RustError.ByReference error = new RustError.ByReference();
        JNA.INSTANCE.in_progress_builder_retract_string(this.validPointer(), entid, keyword, value, error);
        error.logAndConsumeError("InProgressBuilder");
    }

    /**
     * Retracts the value of attribute `keyword` from the provided `value`.
     *
     * TODO throw exception if error occurs
     *
     * @param entid   The `Entid` of the entity to be touched.
     * @param keyword   The name of the attribute in the format `:namespace/name`.
     * @param value The value to be retracted
     */
    public void retract(long entid, String keyword, UUID value) {
        RustError.ByReference error = new RustError.ByReference();
        JNA.INSTANCE.in_progress_builder_retract_uuid(this.validPointer(), entid, keyword, this.getPointerForUUID(value), error);
        error.logAndConsumeError("InProgressBuilder");
    }

    /**
     * Transacts the added assertions. This consumes the pointer associated with this {@link InProgressBuilder}
     * such that no further assertions can be added after the `transact` has completed. To perform
     * further assertions, use the {@link InProgress} inside the {@link InProgressTransactionResult}
     * returned from this function.
     * <p/>
     * This does not commit the transaction. In order to do so, `commit` can be called on the
     * {@link InProgress} inside the {@link InProgressTransactionResult} returned from this function.
     *
     * TODO throw exception if error occurs
     *
     * @return A {@link InProgressTransactionResult} containing the current {@link InProgress} and
     * the {@link TxReport}  generated by the transact.
     */
    public InProgressTransactionResult transact() {
        InProgressTransactionResult result = JNA.INSTANCE.in_progress_builder_transact(this.consumePointer());
        return result;
    }

    /**
     * Transacts the added assertions and commits. This consumes the pointer associated with this
     * {@link InProgressBuilder} and the associated {@link InProgress} such that no further assertions
     * can be added after the `commit` has completed.
     * <p/>
     * To perform further assertions, a new `{@link InProgress} or {@link InProgressBuilder} should be
     * created.
     *
     * TODO throw exception if error occurs
     *
     * @return The {@link TxReport} generated by the commit.
     */
    public TxReport commit() {
        RustError.ByReference error = new RustError.ByReference();
        JNA.TxReport result = JNA.INSTANCE.in_progress_builder_commit(this.consumePointer(), error);
        if (error.isFailure()) {
            Log.e("InProgressBuilder", error.consumeErrorMessage());
            return null;
        }
        return new TxReport(result);
    }

    @Override
    protected void destroyPointer(JNA.InProgressBuilder p) {
        JNA.INSTANCE.in_progress_builder_destroy(p);
    }
}
