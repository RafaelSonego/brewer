package com.rafaelsonego.brewer.thymeleaf.processor;

import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.engine.AttributeName;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.AbstractAttributeTagProcessor;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.spring4.util.FieldUtils;
import org.thymeleaf.templatemode.TemplateMode;

/***
 * 
 * @author rafael Thymeleaf Processor to add has-error class on a field with error
 */
public class ClassForErrorAttributeTagProcessor extends AbstractAttributeTagProcessor {

	private static final String ATTR_NAME = "validatefieldhaserror";
	private static final int PRECEDENT = 1000;

	public ClassForErrorAttributeTagProcessor(String dialectPrefix) {
		super(TemplateMode.HTML, dialectPrefix, null, false, ATTR_NAME, true, PRECEDENT, true);
	}

	@Override
	protected void doProcess(ITemplateContext context, IProcessableElementTag tag, AttributeName attributeName, String attributeValue,
			IElementTagStructureHandler structureHandler) {
		boolean hasError = FieldUtils.hasErrors(context, attributeValue);

		if (hasError) {
			String existingClasses = tag.getAttributeValue("class");
			structureHandler.setAttribute("class", existingClasses + " has-error");
		}
	}

}
