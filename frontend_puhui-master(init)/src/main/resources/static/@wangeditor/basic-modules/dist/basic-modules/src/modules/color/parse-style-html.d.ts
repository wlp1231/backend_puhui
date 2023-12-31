/**
 * @description parse style html
 * @author wangfupeng
 */
import { Descendant } from 'slate';
import { IDomEditor } from '@wangeditor/core';
import { DOMElement } from '../../utils/dom';
export declare function parseStyleHtml(text: DOMElement, node: Descendant, editor: IDomEditor): Descendant;
